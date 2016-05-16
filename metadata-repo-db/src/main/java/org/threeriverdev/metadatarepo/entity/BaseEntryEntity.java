package org.threeriverdev.metadatarepo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
@MappedSuperclass
public class BaseEntryEntity {

    @Id
    @GeneratedValue(generator = "entry-uuid")
    @GenericGenerator(name="entry-uuid", strategy = "uuid")
    private String uuid;

    private String name;

    @Lob
    private String description;

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdTimestamp;

    private String lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastModifiedTimestamp;

    @ElementCollection
    private Set<String> classifications;

    @OneToMany(mappedBy = "entity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<RelationshipEntity> relationships;

    @OneToMany(mappedBy = "entity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<PropertyEntity> properties;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Calendar getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Calendar createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Calendar getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(Calendar lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public Set<String> getClassifications() {
        return classifications;
    }

    public void setClassifications(Set<String> classifications) {
        this.classifications = classifications;
    }

    public List<RelationshipEntity> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<RelationshipEntity> relationships) {
        this.relationships = relationships;
    }

    public List<PropertyEntity> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyEntity> properties) {
        this.properties = properties;
    }
}
