package org.threeriverdev.metadatarepo.core.model;

import java.util.Calendar;
import java.util.Map;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class Artifact {

    private String type;

    private String name;

    private String description;

    private String createdBy;

    private Calendar createdDateTime;

    private String lastModifiedBy;

    private Calendar lastModifiedDateTime;

    // TODO: classifications

    // TODO: relationships

    private Map<String, String> properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Calendar getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Calendar createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Calendar getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(Calendar lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
