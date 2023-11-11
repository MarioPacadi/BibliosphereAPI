package hr.algebra.bibliosphereapi.service.impl;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.models.Rating;
import hr.algebra.bibliosphereapi.repository.BookRepository;
import hr.algebra.bibliosphereapi.repository.CommentRepository;
import hr.algebra.bibliosphereapi.repository.RatingRepository;
import hr.algebra.bibliosphereapi.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;

    public BookService(BookRepository bookRepository, CommentRepository commentRepository, RatingRepository RatingRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = RatingRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Comment> getCommentsByBookId(Long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    public List<Rating> getRatingsByBookId(Long bookId) {
        return ratingRepository.findByBookId(bookId);
    }
}
