package hr.algebra.bibliosphereapi.repository;

import hr.algebra.bibliosphereapi.aspect.SqlInjProtection;
import hr.algebra.bibliosphereapi.models.DogBreed;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Primary
@Repository
public class JdbcDogRepository implements DogRepository {

    private static final String TABLE_NAME = "dog_breed";
    private static final String GENERATED_KEY_COLUMN = "id";

    private static final String SELECT_ALL = "SELECT * from dog_breed";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcDogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(GENERATED_KEY_COLUMN);
    }


    @Override
    public Set<DogBreed> findAll() {
        return Set.copyOf(jdbcTemplate.query(SELECT_ALL, this::mapRowToDog));
    }

    @SqlInjProtection
    @Override
    public Optional<DogBreed> findByName(String dogName) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(SELECT_ALL + " WHERE breed_name = ?", this::mapRowToDog, dogName)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DogBreed> save(DogBreed dog) {
        try {
            dog.setId(saveDogDetails(dog));
            return Optional.of(dog);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    @SqlInjProtection
    @Override
    public Optional<DogBreed> update(String dogName, DogBreed updatedDog) {
        int executed = jdbcTemplate.update("UPDATE dog_breed SET " +
                        "breed_name = ?, " +
                        "breed_type = ?, " +
                        "breed_description = ?, " +
                        "fur_color = ?, " +
                        "origin = ?, " +
                        "min_height_inches = ?, " +
                        "max_height_inches = ?, " +
                        "min_weight_pounds = ?, " +
                        "max_weight_pounds = ?, " +
                        "min_life_span = ?, " +
                        "max_life_span = ?, " +
                        "img_thumb = ?, " +
                        "img_sourceurl = ?, " +
                        "img_attribution = ?, " +
                        "img_creative_commons = ? " +
                        "WHERE breed_name = ?",
                updatedDog.getBreedName(),
                updatedDog.getBreedType(),
                updatedDog.getBreedDescription(),
                updatedDog.getFurColor(),
                updatedDog.getOrigin(),
                updatedDog.getMinHeightInches(),
                updatedDog.getMaxHeightInches(),
                updatedDog.getMinWeightPounds(),
                updatedDog.getMaxWeightPounds(),
                updatedDog.getMinLifeSpan(),
                updatedDog.getMaxLifeSpan(),
                updatedDog.getImgThumb(),
                updatedDog.getImgSourceURL(),
                updatedDog.getImgAttribution(),
                updatedDog.isImgCreativeCommons(),
                dogName
        );

        return executed > 0 ? Optional.of(updatedDog) : Optional.empty();
    }


    @SqlInjProtection
    @Override
    public void deleteByName(String dogName) {
        jdbcTemplate.update("DELETE FROM dog_breed WHERE breed_name = ?",dogName);
    }

    private DogBreed mapRowToDog(ResultSet rs, int rowNum) throws SQLException {
        return new DogBreed(
                rs.getLong("id"),
                rs.getString("breed_name"),
                rs.getString("breed_type"),
                rs.getString("breed_description"),
                rs.getString("fur_color"),
                rs.getString("origin"),
                rs.getDouble("min_height_inches"),
                rs.getDouble("max_height_inches"),
                rs.getDouble("min_weight_pounds"),
                rs.getDouble("max_weight_pounds"),
                rs.getInt("min_life_span"),
                rs.getInt("max_life_span"),
                rs.getString("img_thumb"),
                rs.getString("img_sourceurl"),
                rs.getString("img_attribution"),
                rs.getBoolean("img_creative_commons")
        );
    }


    private long saveDogDetails(DogBreed dog) {
        Map<String, Object> values = new HashMap<>();

        values.put("breed_name", dog.getBreedName());
        values.put("breed_type", dog.getBreedType());
        values.put("breed_description", dog.getBreedDescription());
        values.put("fur_color", dog.getFurColor());
        values.put("origin", dog.getOrigin());
        values.put("min_height_inches", dog.getMinHeightInches());
        values.put("max_height_inches", dog.getMaxHeightInches());
        values.put("min_weight_pounds", dog.getMinWeightPounds());
        values.put("max_weight_pounds", dog.getMaxWeightPounds());
        values.put("min_life_span", dog.getMinLifeSpan());
        values.put("max_life_span", dog.getMaxLifeSpan());
        values.put("img_thumb", dog.getImgThumb());
        values.put("img_sourceurl", dog.getImgSourceURL());
        values.put("img_attribution", dog.getImgAttribution());
        values.put("img_creative_commons", dog.isImgCreativeCommons());

        return simpleJdbcInsert.executeAndReturnKey(values).longValue();
    }
}
