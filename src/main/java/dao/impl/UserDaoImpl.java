package dao.impl;

import dao.UserDao;
import model.User;
import model.UserRole;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.stream.Collectors.toMap;

public class UserDaoImpl implements UserDao {
    private Map<String, User> userMap;

    public UserDaoImpl() {
        userMap = new TreeMap<>();
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

    @Override
    public Map<String, User> getByRole(UserRole userRole) {
        return userMap.values().stream()
                .filter(user -> user.getUserRole().equals(userRole))
                .collect(toMap(User::getUsername, User -> User));
    }

    @Override
    public Map<String, User> getAllUsers() {
        Map<String, User> copyUserMap = new TreeMap<>();
        copyUserMap.putAll(userMap);
        return copyUserMap;
    }
}
