package net.rhuanrocha.speakers;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Path("/speakers")
public interface SpeakerClientService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 5)
    @Timeout(value = 5,unit = ChronoUnit.SECONDS)
    @CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio=0.75, delay = 10000)
    @Fallback(SpeakerListFailback.class)
    List<Speaker> findAll();

    @GET
    @Path("/{speakerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 5)
    @Timeout(value = 5,unit = ChronoUnit.SECONDS)
    @CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio=0.75, delay = 10000)
    @Fallback(SpeakerFailback.class)
    Speaker findById(@PathParam("speakerId") String speakerId );

}
