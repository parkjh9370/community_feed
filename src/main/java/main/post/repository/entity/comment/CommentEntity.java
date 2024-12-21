package main.post.repository.entity.comment;

import jakarta.persistence.ConstraintMode;
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
import main.post.domain.comment.Comment;
import main.post.domain.content.CommentContent;
import main.post.repository.entity.post.PostEntity;
import main.user.repository.entity.UserEntity;

@Entity
@Table(name = "cf_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;

    private Integer likeCount;

    public CommentEntity(Comment comment) {
        this.id = comment.getId();
        this.user = new UserEntity(comment.getAuthor());
        this.post = new PostEntity(comment.getPost());
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
    }

    public Comment toComment() {
        return Comment.builder()
            .id(id)
            .author(user.toUser())
            .post(post.toPost())
            .content(new CommentContent(content))
            .likeCount(new PositiveIntegerCounter(likeCount))
            .build();
    }
}
