package main.post.repository.entity.post;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main.common.domain.PositiveIntegerCounter;
import main.common.repository.entity.TimeBaseEntity;
import main.post.domain.Post;
import main.post.domain.content.PostContent;
import main.post.domain.content.PostPublicationState;
import main.user.repository.entity.UserEntity;

@Entity
@Table(name = "cf_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;

    private String content;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    private Integer likeCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.user = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }

    public Post toPost() {
        return Post.builder()
            .id(id)
            .author(user.toUser())
            .content(new PostContent(content))
            .state(state)
            .likeCount(new PositiveIntegerCounter(likeCount))
            .build();
    }
}
