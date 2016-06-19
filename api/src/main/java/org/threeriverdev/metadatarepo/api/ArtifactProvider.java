package org.threeriverdev.metadatarepo.api;

import java.util.Map;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public interface ArtifactProvider {

    String getType();

    String[] getPropertyKeys();

    // TODO: customRelationshipTypes

    // TODO: customClassifiers

    String getContent(Map<String, Object> properties);

    // TODO: a method that adds Camel consumption routes to the larger Camel context in core
}
