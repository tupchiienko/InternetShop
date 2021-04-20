package service;

import model.User;
import model.UserRole;

import java.util.Map;

public interface UserService {
    Response<User> login(String username, String password);

    Response<User> register(String username, String password);

    Response<User> getUser(String username);

    Response<Map<String, User>> getAllUsers();

    Response<Map<String, User>> getAllUsersByRole(UserRole userRole);

    Response<User> deleteUser(String username);

    Response<User> blockUser(String username);

    Response<User> unblockUser(String username);

    Response<User> changeUsername(String username, String newUsername);

    Response<User> changePassword(String username, String newPassword);
}
