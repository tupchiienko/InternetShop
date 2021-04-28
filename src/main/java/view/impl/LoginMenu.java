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
    private String[] items = {"1.Login", "2.Register"};
    private Scanner scanner;

    @Override
    public void show() {
        showItems(items);
        System.out.println("0. Exit");
        scanner = new Scanner(System.in);
        while (true) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> loginSubMenu();
                case 2 -> registerSubMenu();
                case 0 -> exit();
            }
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

//    private boolean loginCheck(String login, String password) {
//        return userService.login(login, password).isSuccessful();
//    }

    private void loginSubMenu() {
        Response<User> userResponse;
        Scanner scanner = new Scanner(System.in);
        System.out.println("input login:");
        String login = scanner.nextLine();
        System.out.println("input password:");
        String password = scanner.nextLine();
        userResponse=userService.login(login,password);
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

//    private void registerAdminSubMenu(Scanner scanner) {
//        System.out.println("input admins login:");
//        String login = scanner.nextLine();
//        System.out.println("input password:");
//        String password = scanner.nextLine();
//        if(loginCheck(login,password)){
//            User user = userService.login(login,password).getValue();
//            if (user.getUserRole() == UserRole.ADMIN) {
//                System.out.println("input login:");
//                login = scanner.nextLine();
//                System.out.println("input password:");
//                password = scanner.nextLine();
//                System.out.println("repeat password:");
//                String passwordRepeat = scanner.nextLine();
//                if (!passwordRepeat.equals(password)) {
//                    registerSubMenu(this.scanner);
//                } else {
//                    User user = new User(login, password, UserRole.USER);
//                    //add user to map (key - login, value - user
//                    if (userService.register(login, password).isSuccessful()) {
//                        System.out.println("user registered successfully");
//                    } else {
//                        //exception
//                    }
//                }
//            }
//        }
//    }

}
