package org.threeriverdev.metadatarepo.core;

import org.threeriverdev.metadatarepo.api.ArtifactProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class ArtifactRegistry {

    private Map<String, ArtifactProvider> providerMap = new HashMap<>();

    public ArtifactRegistry() {
        // TODO: discover providers using both SE registry and OSGi registry
    }

    public ArtifactProvider getArtifactProvider(String type) {
        return providerMap.get(type);
    }

    public Map<String, ArtifactProvider> getProviderMap() {
        return providerMap;
    }
}
