package main.user.application;

import main.user.application.dto.CreateUserRequestDto;
import main.user.application.dto.GetUserResponseDto;
import main.user.application.interfaces.UserRepository;
import main.user.domain.User;
import main.user.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }
}
