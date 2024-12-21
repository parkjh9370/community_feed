package main.post.repository;

import java.util.Optional;
import main.post.application.interfaces.CommentRepository;
import main.post.domain.comment.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }
}
