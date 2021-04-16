package dao;

import model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> addUser(User user);

    Optional<User> getUser(String username);

    Optional<User> getUser(int id);

    Optional<User> updateUser(int id, User newUser);

    Optional<User> deleteUser(int id);
}
