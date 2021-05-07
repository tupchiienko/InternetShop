package service;

import model.User;

public interface UserService {
    Response<User> login(String username, String password);

    Response<User> register(String username, String password);

    Response<User> blockUser(String username);

    Response<User> unblockUser(String username);
}
