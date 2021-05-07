package view.impl;

import model.User;
import model.UserRole;
import service.OrderService;
import service.ProductService;
import service.Response;
import service.UserService;
import view.Menu;

import java.util.Scanner;

public class LoginMenu implements Menu {
    private final String[] items = {"1.Login", "2.Register", "0. Exit"};

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    public LoginMenu(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void show() {
        showItems(items);
        Scanner scanner = new Scanner(System.in);

        //noinspection InfiniteLoopStatement
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
                new AdminMainMenu(this, userService, orderService, productService).show();
            } else {
                new UserMainMenu(this, user, orderService, productService).show();
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
            System.out.println("Password mismatch! Try again");
            registerSubMenu();
        } else {
            Response<User> registerResponse = userService.register(login, password);
            if (registerResponse.isSuccessful()) {
                new UserMainMenu(this, registerResponse.getValue(), orderService, productService).show();
            } else {
                System.out.println(registerResponse.getMessage());
                registerSubMenu();
            }
        }
    }
}


