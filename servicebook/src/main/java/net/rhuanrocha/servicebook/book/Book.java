package net.rhuanrocha.servicebook.book;

import org.jnosql.artemis.*;

import javax.enterprise.inject.spi.CDI;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Book {
    @Id("id")
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String idAuthor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public void save(){
        if( this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        Database database = DatabaseQualifier.ofDocument();
        BookRepository bookRepository = CDI.current().select(BookRepository.class,database).get();
        bookRepository.save(this);
    }

    public void delete(){
        Database database = DatabaseQualifier.ofDocument();
        BookRepository bookRepository = CDI.current().select(BookRepository.class,database).get();
        bookRepository.deleteById(this.id);
    }
}
