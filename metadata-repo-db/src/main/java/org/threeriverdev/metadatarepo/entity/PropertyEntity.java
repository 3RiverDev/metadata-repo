package org.threeriverdev.metadatarepo.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class PropertyEntity {

    @Id
    @GeneratedValue(generator = "property-uuid")
    @GenericGenerator(name="property-uuid", strategy = "uuid")
    private String uuid;

    private String name;

    private String value;

    @ManyToOne(optional = false)
    private BaseEntryEntity entry;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BaseEntryEntity getEntry() {
        return entry;
    }

    public void setEntry(BaseEntryEntity entry) {
        this.entry = entry;
    }
}
