package main.user.repository.jpa;

import java.util.List;
import main.user.application.dto.GetUserListResponseDto;
import main.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query(value =
        "SELECT new main.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followerUserId = u.id "
            + "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query(value =
        "SELECT new main.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followingUserId = u.id "
            + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
