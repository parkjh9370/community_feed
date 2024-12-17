package main.user.repository.jpa;

import main.user.repository.entity.UserRelationEntity;
import main.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends
    JpaRepository<UserRelationEntity, UserRelationIdEntity> {

}
