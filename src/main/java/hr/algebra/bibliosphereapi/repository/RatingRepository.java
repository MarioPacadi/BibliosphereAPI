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

//    @Query("SELECT r FROM Rating r WHERE r.comment.id = :commentId")
//    List<Rating> findByCommentId(@Param("commentId") Long commentId);
//
//    @Query("SELECT r FROM Rating r WHERE r.comment.book.id = :bookId")
//    List<Rating> findByBookId(@Param("bookId") Long bookId);

    @Modifying
    @Transactional
    @Query("UPDATE Rating r SET r.rating = :newRating WHERE r.id = :ratingId")
    int updateRatingById(@Param("ratingId") Long ratingId, @Param("newRating") Integer newRating);

    @Modifying
    @Transactional
    @Query("DELETE FROM Rating r WHERE r.id = :ratingId")
    void deleteById(@Param("ratingId") Long ratingId);

    @Transactional
    void deleteByComment_BookId(Long bookId);

    @Query("SELECT AVG(r.rating) FROM Rating r JOIN Comment c on c.id = r.comment.id WHERE c.book.id = :bookId")
    Optional<Double> getAvgRating(@Param("bookId") Long bookId);
}
