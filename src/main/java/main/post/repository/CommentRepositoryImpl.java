package main.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import main.post.application.interfaces.CommentRepository;
import main.post.domain.Post;
import main.post.domain.comment.Comment;
import main.post.repository.entity.comment.CommentEntity;
import main.post.repository.jpa.JpaCommentRepository;
import main.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        Post post = comment.getPost();
        CommentEntity commentEntity = new CommentEntity(comment);

        if (comment.getId() != null) {
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return commentEntity.toComment();
        }

        commentEntity = jpaCommentRepository.save(commentEntity);
        jpaPostRepository.increaseCommentCount(post.getId());
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
        return commentEntity.toComment();
    }
}
