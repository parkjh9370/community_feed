package main.post.application.interfaces;

import main.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
