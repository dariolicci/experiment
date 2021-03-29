package it.dl.experiment.neo4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neo4j.ogm.session.Session;

import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import it.dl.experiment.neo4j.model.User;

@ExtendWith(VertxExtension.class)
class UserDaoTest {

    @Test
    void createUserAndRole() {
        Session session = Neo4jSessionFactory.createInstance(
            new JsonObject()
                .put("uri", "http://localhost:7474")
                .put("user", "neo4j")
                .put("password", "maxdata")
                .put("domainClasses", "it.dl.experiment.neo4j.model")
        ).getSession();
        String json = "{'name': 'Test5','roles':[{'role':{'name': 'Admin'}}]}".replaceAll("'", "\"");
        User user = new JsonObject(json).mapTo(User.class);
        // nella relazione setto lo startnode altrimenti mi restituisce un errore
        user.setRoles(
            Optional.ofNullable(user.getRoles()).orElse(new ArrayList<>())
                .stream()
                .map(hasrole -> { 
                    hasrole.setUser(user); 
                    if (hasrole.getCreated()==null) hasrole.setCreated(new Date());
                    return hasrole;
                })
                .collect(Collectors.toList())
            );        
        session.save(user, 1);
        System.out.println(JsonObject.mapFrom(user));
        assertEquals(true, user.getId()!=null);
    }

    @Test
    void updateUser() {
        Session session = Neo4jSessionFactory.createInstance(
            new JsonObject()
                .put("uri", "http://localhost:7474")
                .put("user", "neo4j")
                .put("password", "maxdata")
                .put("domainClasses", "it.dl.experiment.neo4j.model")
        ).getSession();
        String json = "{'name': 'Test5', 'email': 'test@test.it','roles':[{'role':{'id': 7}}]}".replaceAll("'", "\"");
        User user = new JsonObject(json).mapTo(User.class);
        // nella relazione setto lo startnode altrimenti mi restituisce un errore
        user.setRoles(
            Optional.ofNullable(user.getRoles()).orElse(new ArrayList<>())
                .stream()
                .map(hasrole -> { 
                    hasrole.setUser(user); 
                    if (hasrole.getCreated()==null) hasrole.setCreated(new Date());
                    return hasrole;
                })
                .collect(Collectors.toList())
            );        
        session.save(user, 1);
        System.out.println(JsonObject.mapFrom(user));
        assertEquals(true, "test@test.it".equals(user.getEmail()));
    }



}
