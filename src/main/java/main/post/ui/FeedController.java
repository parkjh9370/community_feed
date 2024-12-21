package main.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import main.common.ui.Response;
import main.post.repository.post_queue.UserPostQueueQueryRepository;
import main.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(
        @PathVariable(name = "userId") Long userId,
        Long lastPostId
    ) {
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getContentResponse(
            userId, lastPostId);
        return Response.ok(result);
    }
}
