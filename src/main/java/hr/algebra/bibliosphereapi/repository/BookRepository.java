package hr.algebra.bibliosphereapi.repository;

import hr.algebra.bibliosphereapi.models.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Existing code...

    // Custom query to get all books
    @Query("SELECT b FROM Book b")
    List<Book> getAllBooks();

    // Custom query to get a book by ID
    @Query("SELECT b FROM Book b WHERE b.id = :bookId")
    Optional<Book> getByBookId(Long bookId);

    // Custom query to update a book
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.title = :#{#book.title}, b.author = :#{#book.author}, b.imageUrl = :#{#book.imageUrl} WHERE b.id = :#{#book.id}")
    int updateBook(@Param("book") Book book);

    // Custom query to delete a book
    @Modifying
    @Transactional
    @Query("DELETE FROM Book b WHERE b.id = :#{#book.id}")
    void deleteBook(@Param("book") Book book);

    // Custom query to get the average rating of a book
//    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.comment.book = :book")
//    Double getAverageRating(@Param("book") Book book);
}
