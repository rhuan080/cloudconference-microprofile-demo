package net.rhuanrocha.servicebook;

import net.rhuanrocha.servicebook.book.Book;
import net.rhuanrocha.servicebook.book.BookRepository;
import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class BookService {
    @Inject
    @Database(DatabaseType.DOCUMENT)
    private BookRepository bookRepository;

    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    public Optional<Book> findById(String id){
        return bookRepository.findById(id);
    }
}
