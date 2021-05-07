package dao;

import model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> add(User user);

    Optional<User> getByUsername(String username);

    Optional<User> update(String username, User newUser);

    Optional<User> delete(String username);
}
