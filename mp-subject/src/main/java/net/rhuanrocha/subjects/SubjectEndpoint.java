package net.rhuanrocha.subjects;

import net.rhuanrocha.speakers.SpeakerClientService;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("subjects")
public class SubjectEndpoint {

    @Inject
    private SubjectService subjectService;

    @Inject
    @ConfigProperty(name="service.mpspeaker.path")
    private String pathMpsubject;



    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "subjectCheckedOut")
    @Operation(summary = "Finds Subject by id",
            description = "Each Subject has a id. This query find a subject by id.")
    @APIResponse(description = "The Speaker",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Subject.class)))
    @APIResponse(responseCode = "404", description = "Subject not found")
    public Response findById(@Parameter(required = true,name = "id", description = "Parameter to filter subject by id") @PathParam("id") String id) throws MalformedURLException {

        SpeakerClientService speakerClientService = RestClientBuilder.newBuilder()
                .baseUrl(new URL(pathMpsubject))
                .build(SpeakerClientService.class);

        Subject subject = subjectService.findById(id);

        if(Objects.isNull(subject)){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return Response
                .ok(SubjectDto.build(subject, speakerClientService.findById(subject.getIdSpeaker())))
                .build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "subjectCheckedOut")
    @Operation(summary = "Finds Subjects",
            description = "This query find a subject filtering by parameters.")
    @APIResponse( description = "List of Subjects filtered by parameters",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SubjectDto.class, anyOf = SubjectDto.class)),
            responseCode = "200")
    public Response findByParameters(@Parameter(required = false,name = "nameSpeaker", description = "Parameter to filter subjects by speaker name")
                                @QueryParam("nameSpeaker") String nameSpeaker,
                                     @Parameter(required = false,name = "idSpeaker", description = "Parameter to filter subjects by speaker id")
                                     @QueryParam("idSpeaker") String idSpeaker) throws MalformedURLException {

        SpeakerClientService speakerClientService = RestClientBuilder.newBuilder()
                .baseUrl(new URL(pathMpsubject))
                .build(SpeakerClientService.class);

        if(StringUtils.isNotBlank( idSpeaker)){

            return Response
                    .ok(subjectService
                            .findByIdSpeaker(idSpeaker)
                            .stream()
                            .map(subject -> SubjectDto.build(subject, speakerClientService.findById(subject.getIdSpeaker())))
                            .collect(Collectors.toList()))
                    .build();
        }

       /* if(StringUtils.isNotBlank( nameSpeaker)){

            return Response
                    .ok(subjectService
                            .findByNameSpeaker(idSpeaker)
                            .stream()
                            .map(subject -> SubjectDto.build(subject, speakerClientService.findById(subject.getIdSpeaker())))
                            .collect(Collectors.toList()))
                    .build();
        }*/


        return Response
                .ok(subjectService
                        .findAll()
                        .stream()
                        .map(subject -> SubjectDto.build(subject, speakerClientService.findById(subject.getIdSpeaker())))
                        .collect(Collectors.toList()))
                .build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "subjectCheckedOut")
    @Operation(summary = "Create subject",
            description = "Create a new subject.")
    @APIResponse( description = "List of Subjects filtered by parameters",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Subject.class)),
            responseCode = "201")
    @APIResponse(responseCode = "400", description = "Error to save speaker with data received.")
    public Response save(@RequestBody(
            description = "Created speaker object", required = true,
            content = @Content(
                    schema = @Schema(implementation = Subject.class)))
                         @Valid Subject subject ){

        if( Objects.nonNull(subject.getId()) ){
            throw new WebApplicationException("The JSON is not correct. Id is not allowed.", Response.Status.BAD_REQUEST);
        }

        return Response
                .created(URI.create("speakers/"+ subjectService
                        .create(subject)
                        .getId()))
                .build();

    }
}
