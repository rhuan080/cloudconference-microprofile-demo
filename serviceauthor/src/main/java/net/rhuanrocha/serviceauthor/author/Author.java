package net.rhuanrocha.serviceauthor.author;

import org.jnosql.artemis.*;

import javax.enterprise.inject.spi.CDI;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Author {

    @Id("id")
    private String id;

    @Column
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void save(){
        if( this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        Database database = DatabaseQualifier.ofDocument();
        AuthorRepository authorRepository = CDI.current().select(AuthorRepository.class,database).get();
        authorRepository.save(this);
    }

    public void delete(){
        Database database = DatabaseQualifier.ofDocument();
        AuthorRepository authorRepository = CDI.current().select(AuthorRepository.class,database).get();
        authorRepository.deleteById(this.id);
    }



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
}