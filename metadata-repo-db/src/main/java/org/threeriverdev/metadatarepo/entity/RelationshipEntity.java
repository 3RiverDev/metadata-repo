package org.threeriverdev.metadatarepo.entity;

import org.hibernate.annotations.GenericGenerator;
import org.threeriverdev.metadatarepo.model.core.RelationshipTargetType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
public class RelationshipEntity {

    @Id
    @GeneratedValue(generator = "relationship-uuid")
    @GenericGenerator(name="relationship-uuid", strategy = "uuid")
    private String uuid;

    @Enumerated(value = EnumType.STRING)
    private RelationshipTargetType type;

    private String target;

    @ManyToOne(optional = false)
    private BaseEntryEntity entry;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public RelationshipTargetType getType() {
        return type;
    }

    public void setType(RelationshipTargetType type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public BaseEntryEntity getEntry() {
        return entry;
    }

    public void setEntry(BaseEntryEntity entry) {
        this.entry = entry;
    }
}
