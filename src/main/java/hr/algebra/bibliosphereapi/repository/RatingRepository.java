package hr.algebra.bibliosphereapi.repository;

import hr.algebra.bibliosphereapi.models.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {

    List<Rating> findByBookId(Long bookId);

    Optional<Rating> findById(Long id);

    Optional<Rating> save(Rating rating);

    Optional<Rating> update(Long id, Rating updatedRating);

    void deleteById(Long id);
}
