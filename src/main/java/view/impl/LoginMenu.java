package view.impl;

import model.User;
import model.UserRole;
import service.Response;
import service.UserService;
import view.Menu;

import java.util.Scanner;

public class LoginMenu implements Menu {
    private UserMainMenu userMainMenu;
    private AdminMainMenu adminMainMenu;

    private UserService userService;
    private String[] items = {"1.Login", "2.Register", "0. Exit"};
    private Scanner scanner;

    @Override
    public void show() {
        showItems(items);
        scanner = new Scanner(System.in);
        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> exit();
                case 1 -> loginSubMenu();
                case 2 -> registerSubMenu();

            }
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    private boolean loginCheck(String login, String password) {
        return userService.login(login, password).isSuccessful();
    }

    private void loginSubMenu() {
        Response<User> userResponse;
        Scanner scanner = new Scanner(System.in);
        System.out.println("input login:");
        String login = scanner.nextLine();
        System.out.println("input password:");
        String password = scanner.nextLine();
        userResponse = userService.login(login, password);
        if (userResponse.isSuccessful()) {
            User user = userResponse.getValue();
            if (user.getUserRole() == UserRole.ADMIN) {
                adminMainMenu.show();
            } else {
                userMainMenu.show();
            }
        } else {
            System.out.println("Wrong username/password");
            show();
        }
    }

    private void registerSubMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input login:");
        String login = scanner.nextLine();
        System.out.println("input password:");
        String password = scanner.nextLine();
        System.out.println("repeat password:");
        String passwordRepeat = scanner.nextLine();
        if (!passwordRepeat.equals(password)) {
            System.out.println("Password mismatch!\nTry again.2");
            registerSubMenu();
        } else {
            User user = new User(login, password, UserRole.USER);
            //add user to map (key - login, value - user
            if (userService.register(login, password).isSuccessful()) {
                System.out.println("user registered successfully");
            } else {
                //exception
            }
        }
    }
}


