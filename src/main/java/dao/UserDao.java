package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void addUser(User user);

    List<User> getUser(String username);

    Optional<User> getUser(int id);

    boolean updateUser(int id, User newUser);

    boolean deleteUser(int id);
}
