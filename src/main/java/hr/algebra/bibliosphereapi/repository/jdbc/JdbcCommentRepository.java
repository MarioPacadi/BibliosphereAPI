package hr.algebra.bibliosphereapi.repository.jdbc;

import hr.algebra.bibliosphereapi.aspect.SqlInjProtection;
import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Comment;
import hr.algebra.bibliosphereapi.repository.CommentRepository;
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
public class JdbcCommentRepository implements CommentRepository {

    private static final String TABLE_NAME = "comment";
    private static final String GENERATED_KEY_COLUMN = "id";

    private static final String SELECT_BY_BOOK_ID = "SELECT * FROM comment WHERE book_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM comment WHERE id = ?";
    private static final String UPDATE_COMMENT = "UPDATE comment SET text = ? WHERE id = ?";
    private static final String DELETE_COMMENT = "DELETE FROM comment WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcCommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(GENERATED_KEY_COLUMN);
    }

    @SqlInjProtection
    @Override
    public List<Comment> findByBookId(Long bookId) {
        return jdbcTemplate.query(SELECT_BY_BOOK_ID, this::mapRowToComment, bookId);
    }

    @SqlInjProtection
    @Override
    public Optional<Comment> findById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_BY_ID, this::mapRowToComment, id)
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Comment> save(Comment comment) {
        try {
            comment.setId(saveCommentDetails(comment));
            return Optional.of(comment);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @SqlInjProtection
    @Override
    public Optional<Comment> update(Long id, Comment updatedComment) {
        int executed = jdbcTemplate.update(UPDATE_COMMENT, updatedComment.getText(), id);
        return executed > 0 ? Optional.of(updatedComment) : Optional.empty();
    }

    @SqlInjProtection
    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_COMMENT, id);
    }

//    private Comment mapRowToComment(ResultSet rs, int rowNum) throws SQLException {
//        return new Comment(
//                rs.getLong("id"),
//                rs.getString("text")
//                // add other fields as needed
//        );
//    }

    private Comment mapRowToComment(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("id"));
        comment.setText(rs.getString("text"));

        // Fetch related book
        Long bookId = rs.getLong("book_id");
        Book book = jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", this::mapRowToBook, bookId);
        comment.setBook(book);

        // Add other fields as needed

        return comment;
    }

    private long saveCommentDetails(Comment comment) {
        Map<String, Object> values = new HashMap<>();
        values.put("text", comment.getText());
        // add other fields as needed

        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }
}
