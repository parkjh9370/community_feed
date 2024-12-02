package main.post.application.dto;

public record LikeRequestDto(
    Long targetId,
    Long userId
) {

}
