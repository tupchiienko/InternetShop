package dao.impl;

import dao.UserDao;
import model.User;
import model.UserRole;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class UserDaoImpl implements UserDao {
    private final Map<String, User> userMap;

    public UserDaoImpl() {
        userMap = new TreeMap<>();
        userMap.put("admin", new User("admin", "masterpass", UserRole.ADMIN));
        userMap.put("user1", new User("user1", "12345678", UserRole.USER));
        User blockedUser = new User("user2", "12345678", UserRole.USER);
        blockedUser.block();
        userMap.put("user2", blockedUser);
    }

    @Override
    public Optional<User> add(User user) {
        return Optional.ofNullable(userMap.put(user.getUsername(), user));
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return Optional.ofNullable(userMap.get(username));
    }

    @Override
    public Optional<User> update(String username, User newUser) {
        Optional<User> deletedUser = delete(username);
        if (deletedUser.isPresent()) {
            add(newUser);
        }
        return deletedUser;
    }

    @Override
    public Optional<User> delete(String username) {
        return Optional.ofNullable(userMap.remove(username));
    }
}
