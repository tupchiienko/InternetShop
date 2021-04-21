package dao.impl;

import dao.UserDao;
import exception.UserDataException;
import model.User;
import model.UserRole;

import java.util.Map;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public Optional<User> add(User user) throws UserDataException {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(String username, User newUser) throws UserDataException {
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(String username) {
        return Optional.empty();
    }

    @Override
    public Map<String, User> getByRole(UserRole userRole) {
        return null;
    }

    @Override
    public Map<String, User> getAllUsers() {
        return null;
    }
}
