package hr.algebra.bibliosphereapi.test;

import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.repository.BookRepository;
import hr.algebra.bibliosphereapi.repository.CommentRepository;
import hr.algebra.bibliosphereapi.repository.RatingRepository;
import hr.algebra.bibliosphereapi.service.impl.BookServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private RatingRepository ratingRepository;

    @Test
    public void testGetAllBooks() {
        // Arrange
        List<Book> mockBooks = new ArrayList<>();
        when(bookRepository.getAllBooks()).thenReturn(mockBooks);
        // Mock the behavior of the repository
//        when(bookRepository.getAllBooks()).thenReturn(Arrays.asList(new Book(), new Book()));

        // Act
        List<Book> result = bookService.getAllBooks();

        // Assert
        assertEquals(mockBooks, result);
    }

    @Test
    public void testGetBookById() {
        // Arrange
        Long bookId = 1L;
        Book mockBook = new Book();
        when(bookRepository.getByBookId(bookId)).thenReturn(Optional.of(mockBook));

        // Act
        Optional<Book> result = bookService.getBookById(bookId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockBook, result.get());
    }

    @Test
    public void testGetCommentsByBookId() {
        // Arrange
        Long bookId = 1L;
        List<Comment> mockComments = new ArrayList<>();
        when(commentRepository.findByBookId(bookId)).thenReturn(mockComments);

        // Act
        List<Comment> result = bookService.getCommentsByBookId(bookId);

        // Assert
        assertEquals(mockComments, result);
    }

    @Test
    public void testGetAvgRatingOfBook() {
        // Arrange
        Long bookId = 1L;
        Double mockAvgRating = 4.5;
        when(ratingRepository.getAvgRating(bookId)).thenReturn(Optional.of(mockAvgRating));

        // Act
        Optional<Double> result = bookService.getAvgRatingOfBook(bookId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockAvgRating, result.get());
    }

    @Test
    public void testAddBook() {
        // Arrange
        Book mockBook = new Book();
        when(bookRepository.save(mockBook)).thenReturn(mockBook);

        // Act
        Optional<Book> result = bookService.addBook(mockBook);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockBook, result.get());
    }

    @Test
    public void testUpdateBook() {
        // Arrange
        Book updatedBook = new Book();
        when(bookRepository.updateBook(updatedBook)).thenReturn(1);

        // Act
        bookService.updateBook(updatedBook);

        // Assert (No direct assertions, as it's void method)
        verify(bookRepository, times(1)).updateBook(updatedBook);
    }

    @Test
    public void testDeleteBook() {
        // Arrange
        Long bookId = 1L;

        // Act
        bookService.deleteBook(bookId);

        // Assert (No direct assertions, as it's void method)
        verify(ratingRepository, times(1)).deleteByComment_BookId(bookId);
        verify(commentRepository, times(1)).deleteByBookId(bookId);
        verify(bookRepository, times(1)).deleteBook(bookId);
    }
}

