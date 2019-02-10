package net.rhuanrocha.speakers;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import java.util.Collections;
import java.util.List;

public class SpeakerListFailback implements FallbackHandler<List<Speaker>> {
    @Override
    public List<Speaker> handle(ExecutionContext executionContext) {
        return Collections.emptyList();
    }
}
