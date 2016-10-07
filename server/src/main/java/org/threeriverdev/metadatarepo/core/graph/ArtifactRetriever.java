package org.threeriverdev.metadatarepo.core.graph;

import org.threeriverdev.metadatarepo.core.ArtifactRegistry;
import org.threeriverdev.metadatarepo.core.model.Artifact;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class ArtifactRetriever {

    private ArtifactRegistry artifactRegistry;

    private ArtifactGraph artifactGraph;

    public List<Artifact> getAll() {
        // TODO
        Artifact artifact = new Artifact();
        artifact.setName("Test");
        artifact.setType("text");
        List<Artifact> artifacts = new ArrayList<>();
        artifacts.add(artifact);
        return artifacts;
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
