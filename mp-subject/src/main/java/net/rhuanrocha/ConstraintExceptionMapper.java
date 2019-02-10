package net.rhuanrocha;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

@Provider
public class ConstraintExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<ConstraintViolationException>  {

    @Override
    public Response toResponse(ConstraintViolationException e) {

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity( Collections.singletonMap(
                        "message",
                        e.getConstraintViolations()
                                .stream()
                                .map(constraintViolation -> constraintViolation.getMessage())
                                .reduce((x, y)-> x+"\\n"+ y).orElse(null)))
                .build();
    }
}
