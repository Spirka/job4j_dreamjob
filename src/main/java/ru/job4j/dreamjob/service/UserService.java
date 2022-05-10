package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDbStore;

import java.util.Optional;

/**
 * Class UserService
 *
 * @author Kseniya Dergunova
 * @since 10.05.2022
 */
@ThreadSafe
@Service
public class UserService {

    private final UserDbStore userDbStore;

    public UserService(UserDbStore userDbStore) {
        this.userDbStore = userDbStore;
    }

    public Optional<User> create(User user) {
        return userDbStore.add(user);
    }

}
