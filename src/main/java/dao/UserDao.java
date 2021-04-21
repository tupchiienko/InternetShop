package dao;

import exception.UserDataException;
import model.User;
import model.UserRole;

import java.util.Map;
import java.util.Optional;

public interface UserDao {
    Optional<User> add(User user) throws UserDataException;

    Optional<User> getByUsername(String username);

    Optional<User> update(String username, User newUser) throws UserDataException;

    Optional<User> delete(String username);

    Map<String, User> getByRole(UserRole userRole);

    Map<String, User> getAllUsers();
}
