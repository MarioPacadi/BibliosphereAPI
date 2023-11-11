package hr.algebra.bibliosphereapi.repository;

import hr.algebra.bibliosphereapi.models.Rating;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    // Custom query to find ratings by comment ID
//    @Query("SELECT r FROM Rating r WHERE r.comment.id = :commentId")
//    List<Rating> findByCommentId(@Param("commentId") Long commentId);
//
//    // Custom query to find ratings by book ID
//    @Query("SELECT r FROM Rating r WHERE r.comment.book.id = :bookId")
//    List<Rating> findByBookId(@Param("bookId") Long bookId);

    // Custom query to update the rating of a comment
    @Modifying
    @Transactional
    @Query("UPDATE Rating r SET r.rating = :newRating WHERE r.id = :ratingId")
    int updateRatingById(@Param("ratingId") Long ratingId, @Param("newRating") Integer newRating);

    // Custom query to delete a rating by ID
    @Modifying
    @Transactional
    @Query("DELETE FROM Rating r WHERE r.id = :ratingId")
    void deleteById(@Param("ratingId") Long ratingId);

    @Query("SELECT AVG(r.rating) FROM Rating r JOIN Comment c on c.id = r.comment.id WHERE c.book.id = :bookId")
//    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.comment.book.id = :bookId")
    Optional<Double> getAvgRating(@Param("bookId") Long bookId);
}
