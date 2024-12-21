package main.post.repository.jpa;

import main.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Modifying
    @Query("UPDATE PostEntity p " +
        "SET p.content = :#{#postEntity.getContent()}, " +
        "p.state = :#{#postEntity.getState()}, " +
        "p.update_dt = current_timestamp " +
        "WHERE p.id = :#{#postEntity.getId()}")
    void updatePostEntity(PostEntity postEntity);
}
