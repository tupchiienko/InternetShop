package service;

public interface UserService {

    /**
     * Used to login a user
     * @param username user name
     * @param password user password
     * @return outcome of login - success or not
     */
    boolean login(String username, String password);
}
