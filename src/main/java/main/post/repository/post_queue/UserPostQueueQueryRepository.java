package main.post.repository.post_queue;

import java.util.List;
import main.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {

    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);
}
