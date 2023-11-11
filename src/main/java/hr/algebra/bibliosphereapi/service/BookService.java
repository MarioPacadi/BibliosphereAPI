package hr.algebra.bibliosphereapi.service;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.models.Rating;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();

    public Book getBookById(Long id);

    public List<Comment> getCommentsByBookId(Long bookId);

    public List<Rating> getRatingsByBookId(Long bookId);
}
