package service.impl;

import dao.impl.UserDaoImpl;
import exception.UserAccessException;
import model.UserRole;
import service.Response;
import model.User;
import service.UserService;

import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public Response<User> login(String username, String password) {
        Optional<User> user = userDao.getByUsername(username);
        if (user.isPresent()) {
            User userObj = user.get();
            if (user.get().getPassword().equals(password)) {
                return new Response<>(userObj, true, userObj.toString());
            }
        }
        return new Response<>(null, false, "Incorrect username or password");
    }

    @Override
    public Response<User> register(String username, String password) {
        Optional<User> user = userDao.getByUsername(username);
        if (user.isEmpty()) {
            User newUser;
            try {
                newUser = new User(username, password, UserRole.USER);
            } catch (IllegalArgumentException e) {
                return new Response<>(null, false, e.getMessage());
            }
            userDao.add(newUser);
            return new Response<>(newUser, true, user.toString());
        }
        return new Response<>(null, false, "User already exist");
    }

    @Override
    public Response<User> getUser(String username) {
        Optional<User> user = userDao.getByUsername(username);
        return user.map(value -> new Response<>(value, true, value.toString()))
                .orElse(new Response<>(null, false, "User '" + username + "' does not exist"));
    }

    @Override
    public Response<Map<String, User>> getAllUsers() {
        Map<String, User> userMap = userDao.getAllUsers();
        if (userMap.isEmpty()) {
            return new Response<>(null, false, "User database is empty");
        }
        return new Response<>(userMap, true, userMap.values().toString());
    }

    @Override
    public Response<Map<String, User>> getAllUsersByRole(UserRole userRole) {
        Map<String, User> userMap = userDao.getByRole(userRole);
        if (userMap.isEmpty()) {
            return new Response<>(null, false, "User database is empty");
        }
        return new Response<>(userMap, true, userMap.values().toString());
    }

    @Override
    public Response<User> deleteUser(String username) {
        Optional<User> user = userDao.delete(username);
        return user.map(value -> new Response<>(value, true, value.toString()))
                .orElse(new Response<>(null, false, "User '" + username + "' does not exist"));
    }

    @Override
    public Response<User> blockUser(String username) {
        Optional<User> user = userDao.getByUsername(username);
        if (user.isPresent()) {
            User userToBlock = user.get();
            try {
                userToBlock.block();
            } catch (UserAccessException e) {
                return new Response<>(null, false, e.getMessage());
            }
            userDao.update(username, userToBlock);
            return new Response<>(userToBlock, true, "User '" + username + "' is blocked");
        }
        return new Response<>(null, false, "User '" + username + "' does not exist");
    }

    @Override
    public Response<User> unblockUser(String username) {
        Optional<User> user = userDao.getByUsername(username);
        if (user.isPresent()) {
            User userToBlock = user.get();
            userToBlock.unblock();
            userDao.update(username, userToBlock);
            return new Response<>(userToBlock, true, "User '" + username + "' is unblocked");
        }
        return new Response<>(null, false, "User '" + username + "' does not exist");
    }

    @Override
    public Response<User> changeUsername(String username, String newUsername) {
        Optional<User> user = userDao.getByUsername(username);
        if (user.isPresent()) {
            User userChange = user.get();
            try {
                userChange.setUsername(newUsername);
            } catch (IllegalArgumentException e) {
                return new Response<>(null, false, e.getMessage());
            }
            userDao.update(username, userChange);
            return new Response<>(userChange, true, "Username changed to '"+ newUsername + '\'');
        }
        return new Response<>(null, false, "User '" + username + "' does not exist");
    }

    @Override
    public Response<User> changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> user = userDao.getByUsername(username);
        if (user.isPresent()) {
            User userChange = user.get();
            if (userChange.getPassword().equals(oldPassword)) {
                try {
                    userChange.setPassword(newPassword);
                } catch (IllegalArgumentException e) {
                    return new Response<>(null, false, e.getMessage());
                }
                userDao.update(username, userChange);
                return new Response<>(userChange, true, "User password changed");
            } else {
                return new Response<>(null, false, "You enter incorrect password");
            }
        }
        return new Response<>(null, false, "User '" + username + "' does not exist");
    }
}