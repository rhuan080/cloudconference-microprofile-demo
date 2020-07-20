package net.rhuanrocha.serviceauthor.author;

import org.jnosql.artemis.Repository;

import java.util.List;

public interface AuthorRepository extends Repository<Author, String> {

    public List<Author> findAll();
}
