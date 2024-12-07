package main.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.post.application.dto.LikeRequestDto;
import main.post.application.dto.UpdateCommentRequestDto;
import main.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCreateCommentRequest_whenCreateComment_thenReturnComment() {
        Comment comment = commentService.createComment(commentRequestDto);

        String content = comment.getContent();
        assertEquals(commentContentText, content);
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment() {
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        String updateContent = "this is test update comment";
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(
            post.getId(),
            user.getId(),
            updateContent
        );
        Comment updatedComment = commentService.updateComment(updateCommentRequestDto);

        assertEquals(comment.getId(), updatedComment.getId());
        assertEquals(comment.getAuthor(), updatedComment.getAuthor());
        assertEquals(updateContent, updatedComment.getContent());
    }

    @Test
    void givenComment_whenLikeComment_thenReturnCommentWithLike() {
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUnlikeComment_thenReturnCommentWithLike() {
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        //then
        assertEquals(0, comment.getLikeCount());
    }


}