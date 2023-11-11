package hr.algebra.bibliosphereapi.service.impl;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.models.Rating;
import hr.algebra.bibliosphereapi.repository.BookRepository;
import hr.algebra.bibliosphereapi.repository.CommentRepository;
import hr.algebra.bibliosphereapi.repository.RatingRepository;
import hr.algebra.bibliosphereapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    CommentRepository commentRepository;
    RatingRepository ratingRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CommentRepository commentRepository, RatingRepository RatingRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = RatingRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public Book getBookById(Long id) {
        return bookRepository.getByBookId(id).orElse(null);
    }

    public List<Comment> getCommentsByBookId(Long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    public Optional<Double> getAvgRatingOfBook(Long bookId) {
        return ratingRepository.getAvgRating(bookId);
    }
}
