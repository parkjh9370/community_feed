package main.auth.application;

import lombok.RequiredArgsConstructor;
import main.auth.application.dto.CreateUserAuthRequestDto;
import main.auth.application.interfaces.EmailVerificationRepository;
import main.auth.application.interfaces.UserAuthRepository;
import main.auth.domain.Email;
import main.auth.domain.UserAuth;
import main.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public Long registerUser(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        if (!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("인증되지 않은 이메일 입니다.");
        }

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImageUrl());
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return userAuth.getUserId();
    }
}
