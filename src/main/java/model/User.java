package model;

public class User {
    private String username;
    private String password;
    private boolean isBlocked;
    private final UserRole userRole;

    public User(String userName, String password, UserRole userRole) {
        this.username = userName;
        this.password = password;
        this.userRole = userRole;
        this.isBlocked = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void block() {

    }

    public void unblock() {

    }
}
