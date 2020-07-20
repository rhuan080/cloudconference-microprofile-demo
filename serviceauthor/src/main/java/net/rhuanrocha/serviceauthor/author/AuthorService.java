package net.rhuanrocha.serviceauthor.author;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class AuthorService {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private AuthorRepository authorRepository;

    public List<Author> findAll() {

        return authorRepository.findAll();
    }

    public Optional<Author> findById(String id){
        return authorRepository.findById(id);
    }
}
