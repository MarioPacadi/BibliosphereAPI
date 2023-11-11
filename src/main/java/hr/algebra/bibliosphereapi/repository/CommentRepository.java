package hr.algebra.bibliosphereapi.repository;

import hr.algebra.bibliosphereapi.models.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.book.id = :bookId")
    List<Comment> findByBookId(@Param("bookId") Long bookId);

    @Query("SELECT c FROM Comment c WHERE c.user.id = :userId")
    List<Comment> findByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.text = :newText WHERE c.id = :commentId")
    int updateTextById(@Param("commentId") Long commentId, @Param("newText") String newText);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.id = :commentId")
    void deleteById(@Param("commentId") Long commentId);

}
