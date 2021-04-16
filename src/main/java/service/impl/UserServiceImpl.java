package service.impl;

import model.Response;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public Response<Integer> login(String username, String password) {
        return null;
    }

    @Override
    public Response<Integer> register(String username, String password) {
        return null;
    }

    @Override
    public Response<User> getUserById(int id) {
        return null;
    }

    @Override
    public Response<User> getUserByUsername(String username) {
        return null;
    }

    @Override
    public Response<User> removeUser(int id) {
        return null;
    }

    @Override
    public Response<User> blockUser(int id) {
        return null;
    }

    @Override
    public Response<User> unblockUser(int id) {
        return null;
    }

    @Override
    public Response<User> changeUsername(int id, String username) {
        return null;
    }

    @Override
    public Response<User> changePassword(int id, String username) {
        return null;
    }
}
