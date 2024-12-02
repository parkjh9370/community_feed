package main.post.application.dto;

import main.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(
    Long postId,
    Long userId,
    String content,
    PostPublicationState state
) {

}
