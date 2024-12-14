package main.post.repository.entity.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main.common.repository.entity.TimeBaseEntity;
import main.post.domain.Post;
import main.post.domain.comment.Comment;
import main.user.domain.User;

@Entity
@Table(name = "cf_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likedUser) {
        this.id = new LikeIdEntity(post.getId(), likedUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likedUser) {
        this.id = new LikeIdEntity(comment.getId(), likedUser.getId(), LikeTarget.COMMENT.name());
    }
}
