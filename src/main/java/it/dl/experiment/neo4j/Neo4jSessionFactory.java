package it.dl.experiment.neo4j;

import java.util.Optional;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import io.vertx.core.json.JsonObject;

public class Neo4jSessionFactory {
    
    private static Neo4jSessionFactory instance;

    // private SessionFactory sessionFactory;
    private Session session;

    public static Neo4jSessionFactory getInstance() {
        return instance;
    }

    public static Neo4jSessionFactory createInstance(JsonObject config) {
        if (Neo4jSessionFactory.instance==null) 
            Neo4jSessionFactory.instance = Neo4jSessionFactory.defaultSessionFactory(config);
        return instance;
    }

    private static Neo4jSessionFactory defaultSessionFactory(JsonObject config) {
        Configuration configuration = new Configuration.Builder()
        .uri(config.getString("uri"))
        .credentials(config.getString("user"), config.getString("password"))
        .build();
        return new Neo4jSessionFactory(new SessionFactory(configuration, config.getString("domainClasses")));
    }

    // prevent external instantiation
    private Neo4jSessionFactory(SessionFactory sessionFactory) {
        // this.sessionFactory = sessionFactory;
        session = sessionFactory.openSession();
    }

    public Session getSession() {
        return session;
    }


}
