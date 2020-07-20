package net.rhuanrocha.serviceauthor.author.dtos;

import net.rhuanrocha.serviceauthor.author.Author;

public class AuthorOutDto {

    private String id;
    private String name;

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

    public static AuthorOutDto of(Author author){
        AuthorOutDto authorDto = new AuthorOutDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        return authorDto;
    }
}
