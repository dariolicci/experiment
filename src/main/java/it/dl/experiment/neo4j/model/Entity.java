package it.dl.experiment.neo4j.model;

import java.util.Date;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Version;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

public abstract class Entity {
    @Id @GeneratedValue
    private Long id;

    @DateLong
    private Date created;

    @DateLong
    private Date updated;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


}
