package hr.algebra.bibliosphereapi.repository;

import hr.algebra.bibliosphereapi.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(Book book);

    Optional<Book> update(Long id, Book updatedBook);

    void deleteById(Long id);
}
