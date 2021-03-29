package it.dl.experiment.neo4j.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class User extends Entity {

    // @Properties
    // private Map<String, Object> properties = new HashMap<>();
    
    String name;
    
    @Index(unique=true)
    String email;

    @JsonIgnoreProperties("user")
    @Relationship(type = "HAS_ROLE")
    List<HasRole> roles;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<HasRole> getRoles() {
        return roles;
    }
    public void setRoles(List<HasRole> roles) {
        this.roles = roles;
    }

}
