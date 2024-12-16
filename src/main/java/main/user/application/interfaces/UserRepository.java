package main.user.application.interfaces;

import main.user.domain.User;

public interface UserRepository {

    User save(User user);

    User findById(Long id);
}
