package net.rhuanrocha.speakers;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Objects;


@Path("speakers")
public class SpeakerEndpoint {

    @Inject
    private SpeakerService speakerService;


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "speakersCheckedOut")
    @Operation(summary = "Finds Speakers by id",
            description = "Each Speaker has a id. This query find a speaker by id.")
    @APIResponse(description = "The Speaker",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Speaker.class)))
    @APIResponse(responseCode = "404", description = "Speaker not found")
    public Response findById(@Parameter(required = true,name = "id", description = "Parameter to filter speaker by id") @PathParam("id") String id){

        return Response
                .ok(speakerService.findById(id))
                .build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "speakersCheckedOut")
    @Operation(summary = "Finds Speakers by name",
            description = "Each Speaker has a name. This query find a speaker by name.")
    @APIResponse( description = "List of Speakers filtered by parameters",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Speaker.class, anyOf = Speaker.class)),
                    responseCode = "200")
    public Response findByName(@Parameter(required = false,name = "name", description = "Parameter to filter speakers by name")
                                   @QueryParam("name") String name){

        if( StringUtils.isNotBlank(name) ) {

            return Response
                    .ok(speakerService.findByName(name))
                    .build();
        }

        return Response.ok(speakerService.findAll()).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "speakersCheckedOut")
    @Operation(summary = "Create speaker",
            description = "Create a new speaker.")
    @APIResponse( description = "List of Speakers filtered by parameters",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Speaker.class)),
            responseCode = "201")
    @APIResponse(responseCode = "400", description = "Error to save speaker with data received.")
    public Response save(@RequestBody(
                                    description = "Created speaker object", required = true,
                                    content = @Content(
                                                    schema = @Schema(implementation = Speaker.class)))
                             @Valid Speaker speaker ){

        if( Objects.nonNull(speaker.getId()) ){
            throw new WebApplicationException("The JSON is not correct. Id is not allowed.", Response.Status.BAD_REQUEST);
        }

        return Response
                .created(URI.create("speakers/"+ speakerService
                        .create(speaker)
                        .getId()))
                .build();

    }


}
