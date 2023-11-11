package hr.algebra.bibliosphereapi.repository.jdbc;

import hr.algebra.bibliosphereapi.aspect.SqlInjProtection;
import hr.algebra.bibliosphereapi.models.Book;
import hr.algebra.bibliosphereapi.models.Rating;
import hr.algebra.bibliosphereapi.repository.RatingRepository;
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
public class JdbcRatingRepository implements RatingRepository {

    private static final String TABLE_NAME = "rating";
    private static final String GENERATED_KEY_COLUMN = "id";

    private static final String SELECT_BY_BOOK_ID = "SELECT * FROM rating WHERE book_id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM rating WHERE id = ?";
    private static final String UPDATE_RATING = "UPDATE rating SET rating = ? WHERE id = ?";
    private static final String DELETE_RATING = "DELETE FROM rating WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcRatingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(GENERATED_KEY_COLUMN);
    }

    @SqlInjProtection
    @Override
    public List<Rating> findByBookId(Long bookId) {
        return jdbcTemplate.query(SELECT_BY_BOOK_ID, this::mapRowToRating, bookId);
    }

    @SqlInjProtection
    @Override
    public Optional<Rating> findById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_BY_ID, this::mapRowToRating, id)
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Rating> save(Rating rating) {
        try {
            rating.setId(saveRatingDetails(rating));
            return Optional.of(rating);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @SqlInjProtection
    @Override
    public Optional<Rating> update(Long id, Rating updatedRating) {
        int executed = jdbcTemplate.update(UPDATE_RATING, updatedRating.getRating(), id);
        return executed > 0 ? Optional.of(updatedRating) : Optional.empty();
    }

    @SqlInjProtection
    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_RATING, id);
    }

//    private Rating mapRowToRating(ResultSet rs, int rowNum) throws SQLException {
//        return new Rating(
//                rs.getLong("id"),
//                rs.getInt("rating")
//                // add other fields as needed
//        );
//    }

    private Rating mapRowToRating(ResultSet rs, int rowNum) throws SQLException {
        Rating rating = new Rating();
        rating.setId(rs.getLong("id"));
        rating.setRating(rs.getInt("rating"));

        // Fetch related book
        Long bookId = rs.getLong("book_id");
        Book book = jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", this::mapRowToBook, bookId);
        rating.setBook(book);

        // Add other fields as needed

        return rating;
    }

    private long saveRatingDetails(Rating rating) {
        Map<String, Object> values = new HashMap<>();
        values.put("rating", rating.getRating());
        // add other fields as needed

        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }
}

