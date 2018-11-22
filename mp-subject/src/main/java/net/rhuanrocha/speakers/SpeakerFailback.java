package net.rhuanrocha.speakers;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class SpeakerFailback implements FallbackHandler<Speaker> {


    @Override
    public Speaker handle(ExecutionContext executionContext) {
        return null;
    }
}
