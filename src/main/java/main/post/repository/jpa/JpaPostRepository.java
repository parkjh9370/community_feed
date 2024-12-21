package main.post.repository.jpa;

import java.util.List;
import main.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p.id FROM PostEntity p WHERE p.user.id = :authorId")
    List<Long> findAllPostIdsByAuthorId(Long authorId);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
        "SET p.content = :#{#postEntity.getContent()}, " +
        "p.state = :#{#postEntity.getState()}, " +
        "p.update_dt = current_timestamp " +
        "WHERE p.id = :#{#postEntity.getId()}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
        "SET p.likeCount = :#{#postEntity.likeCount}, " +
        "p.update_dt = now() " +
        "WHERE p.id = :#{#postEntity.getId()}"
    )
    void updateLikeCount(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
        "SET p.commentCount = p.commentCount + 1, " +
        "p.update_dt = now() " +
        "WHERE p.id = :id"
    )
    void increaseCommentCount(Long id);


}
