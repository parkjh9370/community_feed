package main.post.application.interfaces;

import java.util.Optional;
import main.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
