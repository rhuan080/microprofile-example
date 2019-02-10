
package net.rhuanrocha;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.opentracing.Traced;
import org.jnosql.diana.api.Settings;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.api.document.DocumentConfiguration;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.Map;


/**
 * @author rhuanrocha
 */


@OpenAPIDefinition(
        info = @Info(
                title = "MP-Subject",
                version = "1.0",
                description = "Service to Subject.",
                contact = @Contact(
                        name = "Rhuan Rocha",
                        email = "rhuan080@gmail.com",
                        url = "rhuanrocha.net"
                )
        )
)
@ApplicationPath("/")
@ApplicationScoped
public class MicroProfileConfiguration extends Application {

    private static final String COLLECTION = "subjects";

    @Inject
    @ConfigProperty(name ="bd.subject.path",defaultValue = "http://172.18.0.5:8080/")
    private String bdPath;

    private DocumentConfiguration configuration;

    private DocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new MongoDBDocumentConfiguration();
        Map<String, Object> settings = Collections.singletonMap("mongodb-server-host-1", bdPath);
        managerFactory = configuration.get(Settings.of(settings));

    }

    @Produces
    public DocumentCollectionManager getManager() {
        return managerFactory.get(COLLECTION);

    }



}