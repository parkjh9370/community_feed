package main.acceptance;


import static main.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static main.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import main.acceptance.utils.AcceptanceTestTemplate;
import main.post.application.dto.CreatePostRequestDto;
import main.post.domain.content.PostPublicationState;
import main.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /*
     * User1 - follow - User2
     * User1 - follow - User3
     **/
    @BeforeEach
    void setUp() {
        super.init();
    }


    /*
     * User2 Create Post1
     * User1 Get Post1 From Feed
     **/
    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post",
            PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        List<GetPostContentResponseDto> result = requestFeed(1L);

        assertEquals(1, result.size());
        assertEquals(createdPostId, result.getFirst().getId());
    }
}
