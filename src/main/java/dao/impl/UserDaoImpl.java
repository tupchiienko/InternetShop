package dao.impl;

import dao.UserDao;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    Map<Integer, User> userDao = new HashMap<>();

    @Override
    public void addUser(User user) {
        userDao.put(user.getId(),user);
    }

    @Override
    public Optional<User> getUser(String username) {
        if (userDao.containsValue(username)){}

        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(int id) {
        if (userDao.containsKey(id)) {
            return Optional.ofNullable(userDao.get(id));
        }
        return Optional.empty();
    }

    @Override
    public boolean updateUser(int id, User newUser) {
        if (userDao.containsKey(id)) {
            userDao.replace(id, newUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        if (userDao.containsKey(id)) {
            userDao.remove(id);
            return true;
        }
        return false;
    }
}
