package main.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import main.post.application.interfaces.PostRepository;
import main.post.domain.Post;

public class FakePostRepository implements PostRepository {

    private final Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            store.put(post.getId(), post);
            return post;
        }

        long id = store.size() + 1;
        Post newPost = Post.createDefaultPost(id, post.getAuthor(), post.getContent());
        store.put(id, newPost);
        return newPost;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
