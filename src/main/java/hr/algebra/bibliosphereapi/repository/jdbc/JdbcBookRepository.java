package hr.algebra.bibliosphereapi.repository.jdbc;

import hr.algebra.bibliosphereapi.aspect.SqlInjProtection;
import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.models.Rating;
import hr.algebra.bibliosphereapi.repository.BookRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcBookRepository implements BookRepository {

    private static final String TABLE_NAME = "book";
    private static final String GENERATED_KEY_COLUMN = "id";

    private static final String SELECT_ALL = "SELECT * FROM book";
    private static final String SELECT_BY_ID = "SELECT * FROM book WHERE id = ?";
    private static final String UPDATE_BOOK = "UPDATE book SET title = ?, author = ?, imageUrl = ? WHERE id = ?";
    private static final String DELETE_BOOK = "DELETE FROM book WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(GENERATED_KEY_COLUMN);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::mapRowToBook);
    }

    @SqlInjProtection
    @Override
    public Optional<Book> findById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_BY_ID, this::mapRowToBook, id)
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> save(Book book) {
        try {
            book.setId(saveBookDetails(book));
            return Optional.of(book);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @SqlInjProtection
    @Override
    public Optional<Book> update(Long id, Book updatedBook) {
        int executed = jdbcTemplate.update(UPDATE_BOOK,
                updatedBook.getTitle(),
                updatedBook.getAuthor(),
                updatedBook.getImageUrl(),
                id);

        return executed > 0 ? Optional.of(updatedBook) : Optional.empty();
    }

    @SqlInjProtection
    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_BOOK, id);
    }

//    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
//        return new Book(
//                rs.getLong("id"),
//                rs.getString("title"),
//                rs.getString("author"),
//                rs.getString("imageUrl")
//                // add other fields as needed
//        );
//    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setImageUrl(rs.getString("imageUrl"));

        // Note: Do not fetch comments and ratings here to avoid loop
        return book;
    }

    private Book mapRowToBookWithDetails(ResultSet rs, int rowNum) throws SQLException {
        Book book = mapRowToBook(rs, rowNum);

        // Fetch comments for the book
        Long bookId = book.getId();
        List<Comment> comments = jdbcTemplate.query("SELECT * FROM comment WHERE book_id = ?", this::mapRowToComment, bookId);
        book.setComments(comments);

        // Fetch ratings for the book
        List<Rating> ratings = jdbcTemplate.query("SELECT * FROM rating WHERE book_id = ?", this::mapRowToRating, bookId);
        book.setUserRatings(ratings);

        return book;
    }

    private long saveBookDetails(Book book) {
        Map<String, Object> values = new HashMap<>();
        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());
        values.put("imageUrl", book.getImageUrl());
        // add other fields as needed

        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }
}

