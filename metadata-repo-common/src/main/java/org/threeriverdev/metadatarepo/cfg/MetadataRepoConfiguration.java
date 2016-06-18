package org.threeriverdev.metadatarepo.cfg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class MetadataRepoConfiguration {

    public static Properties properties;
    // TODO: use a System property to point to a .properties file

    public static Map<String, Object> getConfigProperties(String prefix) {
        Map<String, Object> filteredProperties = new HashMap<>();
        Iterator<Object> keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            if (key.startsWith(prefix)) {
                filteredProperties.put(key, properties.getProperty(key));
            }
        }
        return filteredProperties;
    }
}
