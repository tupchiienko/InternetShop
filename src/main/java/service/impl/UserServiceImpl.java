package service.impl;

import dao.impl.UserDaoImpl;
import exception.UserAccessException;
import model.User;
import model.UserRole;
import service.Response;
import service.UserService;

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
            if (userObj.isBlocked()) {
                return new Response<>(null, false, "User is blocked");
            }
            if (userObj.getPassword().equals(password)) {
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
}