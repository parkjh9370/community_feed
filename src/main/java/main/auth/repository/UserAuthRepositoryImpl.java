package main.auth.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import main.auth.application.interfaces.UserAuthRepository;
import main.auth.domain.UserAuth;
import main.auth.repository.entity.UserAuthEntity;
import main.auth.repository.jpa.JpaUserAuthRepository;
import main.user.application.interfaces.UserRepository;
import main.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAuth registerUser(UserAuth auth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(auth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);
        return userAuthEntity.toUserAuth();
    }

    @Override
    public UserAuth loginUser(String email, String password) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();

        if (!userAuth.isMatchPassword(password)) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return userAuth;
    }
}
