package hr.algebra.bibliosphereapi.repository;

import hr.algebra.bibliosphereapi.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> findByBookId(Long bookId);

    Optional<Comment> findById(Long id);

    Optional<Comment> save(Comment comment);

    Optional<Comment> update(Long id, Comment updatedComment);

    void deleteById(Long id);
}
