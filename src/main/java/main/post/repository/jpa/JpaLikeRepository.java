package main.post.repository.jpa;

import main.post.repository.entity.like.LikeEntity;
import main.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
    
}
