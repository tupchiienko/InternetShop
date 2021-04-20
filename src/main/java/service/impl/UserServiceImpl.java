package service.impl;

import model.UserRole;
import service.Response;
import model.User;
import service.UserService;

import java.util.Map;

public class UserServiceImpl implements UserService {
    @Override
    public Response<User> login(String username, String password) {
        return null;
    }

    @Override
    public Response<User> register(String username, String password) {
        return null;
    }

    @Override
    public Response<User> getUser(String username) {
        return null;
    }

    @Override
    public Response<Map<String, User>> getAllUsers() {
        return null;
    }

    @Override
    public Response<Map<String, User>> getAllUsersByRole(UserRole userRole) {
        return null;
    }

    @Override
    public Response<User> deleteUser(String username) {
        return null;
    }

    @Override
    public Response<User> blockUser(String username) {
        return null;
    }

    @Override
    public Response<User> unblockUser(String username) {
        return null;
    }

    @Override
    public Response<User> changeUsername(String username, String newUsername) {
        return null;
    }

    @Override
    public Response<User> changePassword(String username, String newPassword) {
        return null;
    }
}
