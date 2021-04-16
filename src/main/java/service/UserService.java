package service;

import model.Response;
import model.User;

public interface UserService {
    Response<Integer> login(String username, String password);

    Response<Integer> register(String username, String password);

    Response<User> getUserById(int id);

    Response<User> getUserByUsername(String username);

    Response<User> removeUser(int id);

    Response<User> blockUser(int id);

    Response<User> unblockUser(int id);

    Response<User> changeUsername(int id, String username);

    Response<User> changePassword(int id, String username);
}
