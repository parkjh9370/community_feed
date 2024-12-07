package main.post.application;

import main.fake.FakeObjectFactory;
import main.post.application.dto.CreateCommentRequestDto;
import main.post.application.dto.CreatePostRequestDto;
import main.post.domain.Post;
import main.post.domain.content.PostPublicationState;
import main.user.application.UserService;
import main.user.application.dto.CreateUserRequestDto;
import main.user.domain.User;

public class PostApplicationTestTemplate {

    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user2", null));

    final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(
        user.getId(),
        "this is test content",
        PostPublicationState.PUBLIC
    );
    final Post post = postService.createPost(postRequestDto);

    final String commentContentText = "this is test comment";
    final CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(
        post.getId(),
        user.getId(),
        commentContentText
    );

}
