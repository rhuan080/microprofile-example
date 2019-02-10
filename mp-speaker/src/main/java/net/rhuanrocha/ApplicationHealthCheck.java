package net.rhuanrocha;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

@Health
@ApplicationScoped
public class ApplicationHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("application-check")
                .up()
                .withData("cpuAvailable", Runtime.getRuntime().availableProcessors())
                .withData( "memoryFree", Runtime.getRuntime().freeMemory())
                .withData("totalMemory", Runtime.getRuntime().totalMemory())
                .build();
    }

}
