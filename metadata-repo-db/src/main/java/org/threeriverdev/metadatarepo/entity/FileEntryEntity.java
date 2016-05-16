package org.threeriverdev.metadatarepo.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * @author Brett Meyer <brett@3riverdev.com>
 */
@Entity
public class FileEntryEntity extends BaseEntryEntity {

    private String contentType;

    private long contentSize;

    @Lob
    private String contentHash;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getContentSize() {
        return contentSize;
    }

    public void setContentSize(long contentSize) {
        this.contentSize = contentSize;
    }

    public String getContentHash() {
        return contentHash;
    }

    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }
}
