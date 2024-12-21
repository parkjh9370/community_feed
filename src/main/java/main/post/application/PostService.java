package main.post.application;

import main.post.application.dto.CreatePostRequestDto;
import main.post.application.dto.LikeRequestDto;
import main.post.application.dto.UpdatePostRequestDto;
import main.post.application.interfaces.LikeRepository;
import main.post.application.interfaces.PostRepository;
import main.post.domain.Post;
import main.user.application.UserService;
import main.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(
        UserService userService,
        PostRepository postRepository,
        LikeRepository likeRepository
    ) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Post post = Post.createPost(null, author, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public Post updatePost(Long id, UpdatePostRequestDto dto) {
        Post post = getPost(id);
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }
}
