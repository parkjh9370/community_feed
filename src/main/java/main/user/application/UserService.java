package main.user.application;

import java.util.IllformedLocaleException;
import main.user.application.dto.CreateUserRequestDto;
import main.user.application.interfaces.UserRepository;
import main.user.domain.User;
import main.user.domain.UserInfo;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.getName(), dto.getProfileImageUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(IllformedLocaleException::new);
    }
}
