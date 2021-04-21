package dao.impl;

import dao.UserDao;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class UserDaoImpl implements UserDao {
    Map<Integer, User> userMap = new HashMap<>();

    @Override
    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public List<User> getUser(String username) {
        return userMap.values()
                .stream()
                .filter(i -> i.getUserName().equals(username))
                .collect(toList());
    }

    @Override
    public Optional<User> getUser(int id) {
        if (userMap.containsKey(id)) {
            return Optional.ofNullable(userMap.get(id));
        }
        return Optional.empty();
    }

    @Override
    public boolean updateUser(int id, User newUser) {
        if (userMap.containsKey(id)) {
            userMap.replace(id, newUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        if (userMap.containsKey(id)) {
            userMap.remove(id);
            return true;
        }
        return false;
    }
}
