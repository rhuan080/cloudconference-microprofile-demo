package net.rhuanrocha.serviceauthor.author.dtos;

import net.rhuanrocha.serviceauthor.author.Author;

import javax.validation.constraints.NotBlank;

public class AuthorInDto {

    @NotBlank(message = "{net.rhuanrocha.Author.name.notblank}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author toEntity(){
        Author author = new Author();
        author.setName(this.name);
        return author;
    }
}
