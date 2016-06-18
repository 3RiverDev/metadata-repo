package org.threeriverdev.metadatarepo.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.artificer.common.ArtificerConfig;
import org.artificer.common.ArtificerException;
import org.artificer.common.error.ArtificerNotFoundException;
import org.artificer.common.ontology.ArtificerOntology;
import org.artificer.repository.hibernate.entity.ArtificerArtifact;
import org.artificer.repository.hibernate.entity.ArtificerStoredQuery;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.engine.spi.SessionImplementor;
import org.threeriverdev.metadatarepo.cfg.MetadataRepoConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    public static abstract class HibernateTask<T> {
        public T execute() throws Exception {
            Session session = null;
            try {
                session = session();

                session.getTransaction().begin();

                T rtn = doExecute(session);

                session.getTransaction().commit();

                return rtn;
            } catch (Exception t) {
                if (session != null) {
                    try {
                        session.getTransaction().rollback();
                    } catch (Throwable t1) {
                        // eat it
                        // TODO: Log it?
                    }
                }
                throw t;
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }

        protected abstract T doExecute(Session session) throws Exception;
    }

    private synchronized static Session session() throws Exception {
        if (sessionFactory == null) {
            // Pass in all hibernate.* settings from artificer.properties
            Map<String, Object> properties = MetadataRepoConfiguration.getConfigProperties("hibernate");

            if (properties.containsKey("hibernate.connection.url")) {
                // If a connection is used, we *cannot* rely on Hibernate's built-in connection pool.  Instead,
                // automatically set up HikariCP.
                initHikariCP(properties);
            }

            entityManagerFactory = new HibernatePersistence().createEntityManagerFactory(persistenceUnit, properties);

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            initDDL(entityManager, properties);
            return entityManager;
        } else {
            return sessionFactory.openSession();
        }
    }

    private static void initHikariCP(Map<String, Object> properties) {
        String connectionUrl = (String) properties.remove("hibernate.connection.url");
        String username = (String) properties.remove("hibernate.connection.username");
        String password = (String) properties.remove("hibernate.connection.password");

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(connectionUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        // In case we're using MySQL, these settings are recommended by HikariCP:
        // TODO: Dial this back if using <512MB RAM!
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("useServerPrepStmts", "true");

        String dialect = (String) properties.get("hibernate.dialect");
        if (dialect != null && dialect.contains("PostgreSQL")) {
            // The JDBC jar verion in the IP BOM does not support Connection.isValid(), so need to use this:
            hikariConfig.setConnectionTestQuery("SELECT 1");
        }

        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        properties.put("hibernate.connection.datasource", hikariDataSource);
    }

    private static void initDDL(EntityManager entityManager, Map<String, Object> properties) throws Exception {
        // If the DDL is not already installed in the DB, automatically do it on first use.
        SessionImplementor session  = (SessionImplementor) entityManager.getDelegate();
        Connection connection = session.connection();
        String schema = (String)properties.get("hibernate.default_schema");

        if (!hasTables(connection, schema)) {
            // our tables don't exist -- create them
            String dialect = (String)properties.get("hibernate.dialect");
            if (dialect != null) {
                String ddlFile;
                if (dialect.contains("PostgreSQL")) {
                    ddlFile = "postgres9.sql";
                } else if (dialect.contains("MySQL")) {
                    ddlFile = "mysql5.sql";
                } else {
                    ddlFile = "h2.sql";
                }

                Statement statement = null;
                LOG.info("INITIALIZING DATABASE WITH SCRIPT: " + ddlFile);
                URL url = HibernateUtil.class.getClassLoader().getResource("ddl/" + ddlFile);
                String ddl = IOUtils.toString(url);

                String[] queries = StringUtils.split(ddl, ";");
                if (queries != null && queries.length > 0) {
                    for (String query : queries) {
                        if (query != null && !query.trim().equals("")) {
                            try {
                                statement = connection.createStatement();
                                if(query != null && !query.trim().equals("")){
                                    statement.executeUpdate(query);
                                }
                            } catch (Exception e) {
                                LOG.error("Exception executing Query:" + query, e);
                                throw e;
                            } finally {
                                if (statement != null) {
                                    statement.close();
                                }
                                // do *not* close the connection -- it will still be
                                // used by this instance of the Session
                            }
                        }
                    }
                }
                LOG.info("END INITIALIZING DATABASE WITH SCRIPT");
            }
        }
    }

    private static boolean hasTables(Connection connection, String schema) throws Exception {
        DatabaseMetaData metadata = connection.getMetaData();

        // check if "ArtificerArtifact" table exists
        ResultSet tables = metadata.getTables(null, schema, "Artifact", null);
        if (tables.next()) {
            return true;
        }

        // also need to check all caps (thanks, Oracle)
        tables = metadata.getTables(null, schema, "ARTIFACT", null);
        if (tables.next()) {
            return true;
        }

        // otherwise, nope
        return false;
    }
}
