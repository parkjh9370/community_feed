package main.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.fake.FakeObjectFactory;
import main.user.application.dto.CreateUserRequestDto;
import main.user.domain.User;
import main.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User savedUser = userService.createUser(dto);
        System.out.println(savedUser);

        //then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();
        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals("test", userInfo.getName());
    }
}