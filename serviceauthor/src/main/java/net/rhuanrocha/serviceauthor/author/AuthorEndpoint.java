package net.rhuanrocha.serviceauthor.author;

import net.rhuanrocha.serviceauthor.author.dtos.AuthorInDto;
import net.rhuanrocha.serviceauthor.author.dtos.AuthorOutDto;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorEndpoint {

    @Inject
    private AuthorService authorService;

    @GET
    @Operation(summary = "Finds all authors")
    @APIResponse(description = "The Author",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY,implementation = AuthorOutDto.class)))
    public Response findAll(){

        return Response
                .ok(authorService
                        .findAll()
                        .stream()
                        .map(AuthorOutDto::of)
                        .collect(Collectors.toList())
                ).build();

    }

    @POST
    @APIResponse(responseCode = "201", description = "Author created.")
    public Response save(@Valid AuthorInDto authorDto) {
        Author author = authorDto.toEntity();
        author.save();
        return Response
                .created(URI.create(String.format("%s/%s","authors", author.getId())))
                .build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Finds author by ID.")
    @APIResponse(description = "The Author",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.OBJECT,implementation = AuthorOutDto.class)))
    @APIResponse(responseCode = "404", description = "Author not found")
    public Response findById(@PathParam("id") String id) {

        Optional<Author> author = authorService.findById(id);
        if(author.isPresent()) {
            return Response
                    .ok(AuthorOutDto.of(author.get()))
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete author by ID.")
    @APIResponse(responseCode = "200", description = "Author removed",
            content = @Content(mediaType = "application/json"))
    @APIResponse(responseCode = "404", description = "Author not found")
    public Response delete (@PathParam("id") String id){
        Optional<Author> author = authorService.findById(id);
        if(author.isPresent()){
            author.get().delete();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
