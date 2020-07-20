package net.rhuanrocha.servicebook;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.jnosql.diana.api.Settings;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@OpenAPIDefinition(info = @Info(
        title = "Cloud Conference Book Service",
        version = "1.0",
        description = "Service to Book",
        contact = @Contact(
                name="Rhuan Rocha",
                email="rhuan080@gmail.com",
                url = "rhuanrocha.net"
        )
))
@ApplicationPath("/api")
public class RestApplication extends Application {

    private final String MONGO_NODE = "mongodb-server-host-1";
    private final String DB = "books";

    @Inject
    @ConfigProperty(name = "book.mongo.host")
    private String host;

    private DocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void init(){
        Map<String, Object> settings = new HashMap<>();
        settings.put(MONGO_NODE,host);
        managerFactory = new MongoDBDocumentConfiguration().get(Settings.of(settings));
    }

    @Produces
    public DocumentCollectionManager getManager(){
        return managerFactory.get(DB);
    }
}
