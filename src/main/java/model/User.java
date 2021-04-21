package model;

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
            throw new IllegalArgumentException("");
        }
        if (!username.matches("[a-zA-Z0-9_]")) {
            throw new IllegalArgumentException("");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password.length() > 32 || password.length() < 8) {
            throw new IllegalArgumentException("");
        }
        if (!password.matches("[a-zA-Z0-9_]")) {
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
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
