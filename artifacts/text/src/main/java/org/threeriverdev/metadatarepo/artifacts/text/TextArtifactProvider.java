package org.threeriverdev.metadatarepo.artifacts.text;

import org.threeriverdev.metadatarepo.api.ArtifactProvider;

import java.util.Map;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class TextArtifactProvider implements ArtifactProvider {

    @Override
    public String getType() {
        return "text";
    }

    @Override
    public String[] getPropertyKeys() {
        return new String[] { "content" };
    }

    @Override
    public String getContent(Map<String, Object> properties) {
        return (String) properties.get("content");
    }
}
