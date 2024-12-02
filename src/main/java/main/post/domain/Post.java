package main.post.domain;

import main.common.domain.PositiveIntegerCounter;
import main.post.domain.content.Content;
import main.post.domain.content.PostContent;
import main.post.domain.content.PostPublicationState;
import main.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public static Post createPost(
        Long id,
        User author,
        String content,
        PostPublicationState state
    ) {
        return new Post(id, author, new PostContent(content), state);
    }

    public static Post createDefaultPost(
        Long id,
        User author,
        String content
    ) {
        return new Post(id, author, new PostContent(content), PostPublicationState.PUBLIC);
    }

    protected Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC);
    }

    protected Post(Long id, User author, Content content, PostPublicationState state) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCount.increase();
    }

    public void unlike() {
        likeCount.decrease();
    }

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.state = state;
        this.content.updateContent(updateContent);
    }

    public int getLikeCount() {
        return likeCount.getCount();
    }

    public String getContent() {
        return content.getContentText();
    }
}
