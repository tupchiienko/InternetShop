package dao.impl;

import dao.UserDao;
import model.User;

import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public Optional<User> addUser(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateUser(int id, User newUser) {
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteUser(int id) {
        return Optional.empty();
    }
}
