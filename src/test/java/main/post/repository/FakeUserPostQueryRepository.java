//package main.post.repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import main.post.repository.entity.post.PostEntity;
//import main.post.repository.post_queue.UserPostQueueQueryRepository;
//import main.post.ui.dto.GetPostContentResponseDto;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@Profile("test")
//public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {
//
//    private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;
//
//    public FakeUserPostQueryRepository(FakeUserQueueRedisRepository fakeUserQueueRedisRepository) {
//        this.fakeUserQueueRedisRepository = fakeUserQueueRedisRepository;
//    }
//
//    @Override
//    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
//        List<PostEntity> postEntities = fakeUserQueueRedisRepository.getPostListByUserId(userId);
//        List<GetPostContentResponseDto> result = new ArrayList<>();
//
//        for (PostEntity postEntity : postEntities) {
//            GetPostContentResponseDto responseDto = GetPostContentResponseDto.builder()
//                .id(postEntity.getId())
//                .build();
//            result.add(responseDto);
//        }
//        return result;
//    }
//}
