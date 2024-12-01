package main.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import main.post.domain.Post;
import main.post.domain.content.CommentContent;
import main.post.domain.content.PostContent;
import main.user.domain.User;
import main.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {

    private final UserInfo userInfo = new UserInfo("name", "url");
    private final User user1 = new User(1L, userInfo);
    private final User user2 = new User(2L, userInfo);

    private final Post post = new Post(1L, user1, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user1, new CommentContent("content"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1() {
        comment.like(user2);

        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeSelf_thenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> comment.like(user1));
    }

    @Test
    void givenCommentCreatedLike_whenUnLike_thenLikeShouldBe0() {
        comment.like(user2);

        comment.unlike();

        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUpdateComment_thenShouldBeUpdated() {
        String newCommentContent = "new content!";

        comment.updateContent(user1, newCommentContent);

        assertEquals(newCommentContent, comment.getContent());
    }

    @Test
    void givenComment_whenUpdateCommentOver100_thenThrowError() {
        String newCommentContent = "r".repeat(101);

        assertThrows(IllegalArgumentException.class,
            () -> comment.updateContent(user1, newCommentContent));
    }
}