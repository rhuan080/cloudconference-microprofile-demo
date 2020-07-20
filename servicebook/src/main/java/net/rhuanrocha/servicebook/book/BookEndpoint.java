package net.rhuanrocha.servicebook.book;

import net.rhuanrocha.servicebook.BookService;
import net.rhuanrocha.servicebook.author.AuthorService;
import net.rhuanrocha.servicebook.book.dtos.BookInDto;
import net.rhuanrocha.servicebook.book.dtos.BookOutDto;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookEndpoint {

    @Inject
    private BookService bookService;

    @Inject
    private AuthorService authorService;

    @GET
    public Response findAll(){
        return Response
                .ok(bookService
                        .findAll()
                        .stream()
                        .map(book -> BookOutDto.of(book, authorService.findById(book.getIdAuthor())))
                        .collect(Collectors.toList()))
                .build();
    }

    @POST
    public Response save( @Valid BookInDto bookDto){
        Book book = bookDto.toEntity();
        book.save();
        return Response
                .created(URI.create(String.format("/books/%s",book.getId())))
                .build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete (@PathParam("id") String id){
        Optional<Book> book = bookService.findById(id);
        if(book.isPresent()){
            book.get().delete();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id, @Valid BookInDto bookDto){
        Optional<Book> book = bookService.findById(id);
        if(!book.isPresent()){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        Book bookEntity = book.get();
        bookEntity.setName(bookDto.getName());
        bookEntity.setDescription(bookDto.getDescription());
        bookEntity.setIdAuthor(bookDto.getIdAuthor());
        bookEntity.save();
        return Response.ok().build();
    }
}
