package net.rhuanrocha.servicebook.book.dtos;

import net.rhuanrocha.servicebook.author.AuthorDto;
import net.rhuanrocha.servicebook.book.Book;

public class BookOutDto {
    private String id;
    private String name;
    private String description;
    private AuthorDto author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto authorDto) {
        this.author = authorDto;
    }

    public static BookOutDto of (Book book){
        BookOutDto bookDto = new BookOutDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setDescription(book.getDescription());
        //bookDto.setIdAuthor(book.getIdAuthor());
        return bookDto;
    }

    public static BookOutDto of (Book book, AuthorDto author){
        BookOutDto bookDto = new BookOutDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setDescription(book.getDescription());
        bookDto.setAuthor(author);
        return bookDto;
    }




}
