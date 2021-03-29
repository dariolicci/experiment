package it.dl.experiment.neo4j.model;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Role extends Entity {

    String name;

    // @Relationship(type = "CAN")
    // List<Permission> permissions;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
