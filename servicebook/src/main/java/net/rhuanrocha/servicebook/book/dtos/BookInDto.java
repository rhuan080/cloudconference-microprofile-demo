package net.rhuanrocha.servicebook.book.dtos;

import net.rhuanrocha.servicebook.book.Book;

import javax.validation.constraints.NotBlank;

public class BookInDto {

    @NotBlank(message = "{net.rhuanrocha.Book.name.notblank}")
    private String name;

    @NotBlank(message = "{net.rhuanrocha.Book.description.notblank}")
    private String description;

    @NotBlank(message = "{net.rhuanrocha.Book.idAuthor.notblank}")
    private String idAuthor;

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

    public String getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(String idAuthor) {
        this.idAuthor = idAuthor;
    }


    public Book toEntity(){
        Book book = new Book();
        book.setName(this.name);
        book.setDescription(this.description);
        book.setIdAuthor(this.idAuthor);
        return book;
    }

}
