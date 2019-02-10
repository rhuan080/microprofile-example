package net.rhuanrocha.speakers;

import io.opentracing.Tracer;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.opentracing.ClientTracingRegistrar;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.time.temporal.ChronoUnit;


public class SpeakerClientRest {

    @Inject
    @ConfigProperty(name = "net.rhuanrocha.speakers.SpeakerClientService/mp-rest/url")
    private String path;

    @Inject
    private Tracer configuredTracer;


    @Retry(maxRetries = 2)
    @Timeout(value = 5,unit = ChronoUnit.SECONDS)
    @CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio=0.75, delay = 60, delayUnit = ChronoUnit.SECONDS)
    @Fallback(SpeakerFailback.class)
    public Speaker findById( String speakerId ){
        Client client = ClientTracingRegistrar.configure(ClientBuilder.newBuilder()).build();
        Speaker response = null;
        try {
            response = client.target(path)
                    .path("/speakers/"+speakerId)
                    .request()
                    .get(Speaker.class);



        }
        finally {
            client.close();
        }
        return response;
    }
}
