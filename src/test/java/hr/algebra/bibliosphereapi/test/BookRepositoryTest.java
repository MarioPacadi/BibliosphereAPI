package hr.algebra.bibliosphereapi.test;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.repository.BookRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    private BookRepository bookRepository;

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @Test
    public void testGetBookById() {
        // Save a test book to the in-memory database
        Book savedBook = bookRepository.save(new Book("Test Book", "Test Author"));

        // Perform the repository method
        Optional<Book> result = bookRepository.getByBookId(savedBook.getId());

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(savedBook.getId(), result.get().getId());
    }

    @Test
    public void testUpdateBook() {
        // Save a test book to the in-memory database
        Book savedBook = bookRepository.save(new Book("Test Book", "Test Author"));

        // Modify the book
        savedBook.setTitle("Updated Title");
        savedBook.setAuthor("Updated Author");

        // Perform the repository method
        int updatedRows = bookRepository.updateBook(savedBook);

        // Assertions
        assertEquals(1, updatedRows);

        // Fetch the book again
        Optional<Book> updatedBook = bookRepository.getByBookId(savedBook.getId());
        assertTrue(updatedBook.isPresent());
        assertEquals("Updated Title", updatedBook.get().getTitle());
        assertEquals("Updated Author", updatedBook.get().getAuthor());
    }

    @Test
    public void testDeleteBook() {
        // Save a test book to the in-memory database
        Book savedBook = bookRepository.save(new Book("Test Book", "Test Author"));

        // Perform the repository method
        bookRepository.deleteBook(savedBook.getId());

        // Fetch the book again
        Optional<Book> deletedBook = bookRepository.getByBookId(savedBook.getId());

        // Assertions
        assertFalse(deletedBook.isPresent());
    }

    // Add more tests for other methods in BookRepository
}


