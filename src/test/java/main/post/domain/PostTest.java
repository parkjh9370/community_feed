package main.post.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import main.post.domain.content.PostContent;
import main.post.domain.content.PostPublicationState;
import main.user.domain.User;
import main.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class PostTest {

    private final UserInfo userInfo = new UserInfo("name", "url");
    private final User user1 = new User(1L, userInfo);
    private final User user2 = new User(2L, userInfo);

    private final Post post = new Post(1L, user1, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
        post.like(user2);

        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeUser2_thenLikeCountShouldBe1() {
        assertThrows(IllegalArgumentException.class, () -> post.like(user1));
    }

    @Test
    void givenPostCreatedAndLike_whenUnLike_thenLikeCountShouldBe0() {
        post.like(user2);

        post.unlike();

        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUnLike_thenLikeCountShouldBe0() {
        post.unlike();

        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenLikeShouldBeUpdated() {
        String newPostContent = "new Content";

        post.updatePost(user1, newPostContent, PostPublicationState.PUBLIC);

        assertEquals(newPostContent, post.getContent());
    }

    @Test
    void givenPostCreated_whenOtherUpdateContent_thenThrowException() {
        String newPostContent = "new new";

        assertThrows(IllegalArgumentException.class,
            () -> post.updatePost(user2, newPostContent, PostPublicationState.PUBLIC));
    }
}