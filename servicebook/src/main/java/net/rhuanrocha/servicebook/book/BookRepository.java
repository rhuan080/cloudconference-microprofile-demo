package net.rhuanrocha.servicebook.book;

import org.jnosql.artemis.Repository;

import java.util.List;

public interface BookRepository extends Repository<Book, String> {

    public List<Book> findAll();
}
