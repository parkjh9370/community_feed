package main.acceptance.utils;

import static main.acceptance.steps.UserAcceptanceSteps.createUser;
import static main.acceptance.steps.UserAcceptanceSteps.followUser;

import main.user.application.dto.CreateUserRequestDto;
import main.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
