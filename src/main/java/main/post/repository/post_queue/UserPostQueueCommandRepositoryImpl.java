package main.post.repository.post_queue;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import main.post.repository.entity.post.PostEntity;
import main.post.repository.entity.post.UserPostQueueEntity;
import main.post.repository.jpa.JpaPostRepository;
import main.post.repository.jpa.JpaUserPostQueueRepository;
import main.user.repository.entity.UserEntity;
import main.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;
//    private final UserQueueRedisRepository queueRedisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getUser();
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntityList = followerIds.stream()
            .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
            .toList();
        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
//        queueRedisRepository.publishPostToFollowingUserList(postEntity, followerIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postEntities = jpaPostRepository.findAllPostIdsByAuthorId(targetId);

        List<UserPostQueueEntity> userPostQueueEntityList = postEntities.stream()
            .map(postEntity -> new UserPostQueueEntity(userId, postEntity.getId(), targetId))
            .toList();
        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);
//        queueRedisRepository.publishPostListToFollowerUser(postEntities, userId);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
//        queueRedisRepository.deleteFeed(userId, targetId);
    }
}
