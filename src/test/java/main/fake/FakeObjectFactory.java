package main.fake;

import main.post.application.CommentService;
import main.post.application.PostService;
import main.post.application.interfaces.CommentRepository;
import main.post.application.interfaces.LikeRepository;
import main.post.application.interfaces.PostRepository;
import main.post.repository.FakeCommentRepository;
import main.post.repository.FakeLikeRepository;
import main.post.repository.FakePostRepository;
import main.user.application.UserRelationService;
import main.user.application.UserService;
import main.user.application.interfaces.UserRelationRepository;
import main.user.application.interfaces.UserRepository;
import main.user.repository.FakeUserRelationRepository;
import main.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(
        userService,
        fakeUserRelationRepository
    );
    private static final PostService postService = new PostService(
        userService,
        fakePostRepository,
        fakeLikeRepository
    );
    private static final CommentService commentService = new CommentService(
        userService,
        postService,
        fakeCommentRepository,
        fakeLikeRepository
    );

    private FakeObjectFactory() {
    }

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
