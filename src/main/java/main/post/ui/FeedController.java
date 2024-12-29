package main.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import main.common.principal.AuthPrincipal;
import main.common.principal.UserPrincipal;
import main.common.ui.Response;
import main.post.repository.post_queue.UserPostQueueQueryRepository;
import main.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("")
    public Response<List<GetPostContentResponseDto>> getPostFeed(
        // @PathVariable(name = "userId") Long userId,
        @AuthPrincipal UserPrincipal userPrincipal,
        Long lastPostId
    ) {
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getContentResponse(
            userPrincipal.getUserId(), lastPostId);
        return Response.ok(result);
    }
}
