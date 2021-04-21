package model;

import exception.UserAccessException;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private boolean isBlocked;
    private final UserRole userRole;

    public User(String username, String password, UserRole userRole) {
        setUsername(username);
        setPassword(password);
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
        if (username.length() > 32 || username.length() < 4) {
            throw new IllegalArgumentException("Username length must be from 4 to 32.");
        }
        if (!username.matches("[a-zA-Z0-9_]")) {
            throw new IllegalArgumentException("Username can contain only letters(a-z), numbers(0-9), symbol( _ ).");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password.length() > 32 || password.length() < 8) {
            throw new IllegalArgumentException("Password length must be from 8 to 32.");
        }
        if (!password.matches("[a-zA-Z0-9_]")) {
            throw new IllegalArgumentException("Password can contain only letters(a-z), numbers(0-9), symbol( _ ).");
        }
        this.password = password;
    }

    public void block() {
        if (userRole == UserRole.ADMIN) {
            throw new UserAccessException("Admin can not be blocked.");
        }
        isBlocked = true;
    }

    public void unblock() {
        isBlocked = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
