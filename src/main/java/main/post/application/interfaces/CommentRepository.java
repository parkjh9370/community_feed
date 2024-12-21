package main.post.application.interfaces;

import main.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findById(Long id);
}
