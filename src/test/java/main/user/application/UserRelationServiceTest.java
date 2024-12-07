package main.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import main.fake.FakeObjectFactory;
import main.user.application.dto.CreateUserRequestDto;
import main.user.application.dto.FollowUserRequestDto;
import main.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);
        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenFollowSaved() {
        //when
        userRelationService.follow(requestDto);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenThrowUser() {
        // given
        userRelationService.follow(requestDto);

        assertThrows(
            IllegalArgumentException.class,
            () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenThrowUser() {
        FollowUserRequestDto sameUserRequestDto = new FollowUserRequestDto(user1.getId(),
            user1.getId());

        assertThrows(IllegalArgumentException.class,
            () -> userRelationService.follow(sameUserRequestDto));
    }

    @Test
    void givenCreateTwoUserFollow_whenUnFollow_thenFollowSaved() {
        //given
        userRelationService.follow(requestDto);

        //when
        userRelationService.unfollow(requestDto);

        //then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnFollow_thenThrowUser() {

        assertThrows(
            IllegalArgumentException.class,
            () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnFollowSelf_thenThrowUser() {
        FollowUserRequestDto sameUserRequestDto = new FollowUserRequestDto(user1.getId(),
            user1.getId());

        assertThrows(IllegalArgumentException.class,
            () -> userRelationService.unfollow(sameUserRequestDto));
    }
}