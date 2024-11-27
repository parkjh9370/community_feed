package main.user.application.dto;

import java.util.Objects;

public class FollowUserRequestDto {

    private final Long userId;
    private final Long targetUserId;

    public FollowUserRequestDto(Long userId, Long targetUserId) {
        this.userId = userId;
        this.targetUserId = targetUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FollowUserRequestDto that = (FollowUserRequestDto) o;
        return Objects.equals(userId, that.userId) && Objects.equals(targetUserId,
            that.targetUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, targetUserId);
    }
}
