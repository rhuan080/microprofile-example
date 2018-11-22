package net.rhuanrocha;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {

        if( e instanceof WebApplicationException) {
            return Response
                    .fromResponse(((WebApplicationException)e).getResponse())
                    .entity(Collections.singletonMap("message",e.getMessage()))
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
    }
}
