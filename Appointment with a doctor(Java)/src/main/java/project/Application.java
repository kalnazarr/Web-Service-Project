package project;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Application extends ResourceConfig {
    public Application(){
        register(new ApplicationBinder());
        packages("controllers");
    }
}
