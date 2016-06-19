package org.threeriverdev.metadatarepo.core.graph;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class ArtifactGraph {

    private final GraphDatabaseService graphDb;

    public ArtifactGraph() {
        // TODO
        File dbStorage = new File("/tmp/neo4j");
        if (!dbStorage.exists()) {
            dbStorage.mkdir();
        }
        graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(dbStorage);

        // TODO: Add indexes for most built-in properties

        // Suggested by neo4j docs...
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }

    public GraphDatabaseService getGraph() {
        return graphDb;
    }
}
