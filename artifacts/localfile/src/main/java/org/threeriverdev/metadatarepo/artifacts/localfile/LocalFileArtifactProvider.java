package org.threeriverdev.metadatarepo.artifacts.localfile;

import org.apache.commons.io.FileUtils;
import org.threeriverdev.metadatarepo.api.ArtifactProvider;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class LocalFileArtifactProvider implements ArtifactProvider {

    @Override
    public String getType() {
        return "localfile";
    }

    @Override
    public String[] getPropertyKeys() {
        return new String[] { "localFilePath", "localFileCharset" };
    }

    @Override
    public String getContent(Map<String, Object> properties) {
        String localFilePath = (String) properties.get("localFilePath");
        String localFileCharset = (String) properties.get("localFileCharset");

        File file = new File(localFilePath);
        if (file.exists()) {
            try {
                return FileUtils.readFileToString(file, Charset.forName(localFileCharset));
            } catch (IOException e) {
                // TODO
                e.printStackTrace();
            }
        }
        return "";
    }
}
