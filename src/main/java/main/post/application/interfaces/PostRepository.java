package main.post.application.interfaces;

import java.util.Optional;
import main.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);
}
