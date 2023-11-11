package hr.algebra.bibliosphereapi.service;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.models.Rating;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> getAllBooks();

    public Book getBookById(Long id);

    public List<Comment> getCommentsByBookId(Long bookId);

    public Optional<Double> getAvgRatingOfBook(Long bookId);
}
