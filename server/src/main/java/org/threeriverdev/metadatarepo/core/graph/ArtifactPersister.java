package org.threeriverdev.metadatarepo.core.graph;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.threeriverdev.metadatarepo.api.ArtifactProvider;
import org.threeriverdev.metadatarepo.core.ArtifactRegistry;
import org.threeriverdev.metadatarepo.core.model.Artifact;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class ArtifactPersister {

    private ArtifactRegistry artifactRegistry;

    private ArtifactGraph artifactGraph;

    public void persist(Artifact artifact) {
        ArtifactProvider artifactProvider = artifactRegistry.getArtifactProvider(artifact.getType());

        Transaction tx = artifactGraph.getGraph().beginTx();

        try {
            Node node = artifactGraph.getGraph().createNode();
            node.setProperty("type", artifactProvider.getType());
            node.setProperty("name", artifact.getName());
            node.setProperty("description", artifact.getDescription());
            node.setProperty("createdBy", artifact.getCreatedBy());
            node.setProperty("createdDateTime", artifact.getCreatedDateTime());
            node.setProperty("lastModifiedBy", artifact.getLastModifiedBy());
            node.setProperty("lastModifiedDateTime", artifact.getLastModifiedDateTime());
            for (String key : artifact.getProperties().keySet()) {
                node.setProperty(key, artifact.getProperties().get(key));
            }
            // TODO: relationships
            // TODO: classifications

            tx.success();
        } catch (Exception e) {
            tx.failure();
        } finally {
            tx.close();
        }
    }

    public ArtifactRegistry getArtifactRegistry() {
        return artifactRegistry;
    }

    public void setArtifactRegistry(ArtifactRegistry artifactRegistry) {
        this.artifactRegistry = artifactRegistry;
    }

    public ArtifactGraph getArtifactGraph() {
        return artifactGraph;
    }

    public void setArtifactGraph(ArtifactGraph artifactGraph) {
        this.artifactGraph = artifactGraph;
    }
}
