package model;

import java.util.Objects;

public class User {
    private String userName;
    private String password;
    private boolean isBlocked;
    private final UserRole userRole;

    public User(String userName, String password, UserRole userRole) {
        setUserName(userName);
        setPassword(password);
        this.userRole = userRole;
        this.isBlocked = false;
    }

    public String getUserName() {
        return userName;
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

    public void setUserName(String userName) {
        if (userName.length() > 32 || userName.length() < 4) {
            throw new IllegalArgumentException("");
        }
        if (!userName.matches("[a-zA-Z0-9_]")) {
            throw new IllegalArgumentException("");
        }
        this.userName = userName;
    }

    public void setPassword(String password) {
        if (userName.length() > 32 || userName.length() < 8) {
            throw new IllegalArgumentException("");
        }
        if (!userName.matches("[a-zA-Z0-9_]")) {
            throw new IllegalArgumentException("");
        }
        this.password = password;
    }

    public void block() {
        if (userRole != UserRole.ADMIN) {
            isBlocked = true;
        }
    }

    public void unblock() {
        isBlocked = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
