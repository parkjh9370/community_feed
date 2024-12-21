package main.post.repository.jpa;

import main.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
        + "SET c.content = :#{#commentEntity.getContent()}, "
        + "c.update_dt = now() "
        + "WHERE c.id = :#{#commentEntity.getId()}")
    void updateCommentEntity(CommentEntity commentEntity);

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
        + "SET c.likeCount = :#{#commentEntity.getLikeCount()}, "
        + "c.update_dt = now() "
        + "WHERE c.id = :#{#commentEntity.getId()}")
    void updateLikeCount(CommentEntity commentEntity);
}
