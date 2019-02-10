package net.rhuanrocha.speakers;

import io.opentracing.Span;
import io.opentracing.tag.Tags;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;



public class SpeakerFailback implements FallbackHandler<Speaker> {


    @Inject
    private io.opentracing.Tracer configuredTracer;

    @Override
    public Speaker handle(ExecutionContext executionContext) {
       Span span = Optional.ofNullable(configuredTracer.activeSpan())
               .orElse(configuredTracer.buildSpan(
                       executionContext.getMethod().getName())
                       .startActive(false).span());

       span.setTag(Tags.ERROR.getKey(), true)
                .log("Error to call "+executionContext.getMethod().getName());

       span.finish();

       return null;
    }
}
