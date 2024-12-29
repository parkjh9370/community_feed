package main.auth.application.interfaces;

import main.auth.domain.UserAuth;
import main.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth auth, User user);
}
