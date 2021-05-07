package view.impl;

import model.User;
import service.OrderService;
import service.ProductService;
import service.UserService;
import view.Menu;

import java.util.Scanner;

public class UserMainMenu implements Menu {
    private final String[] items = {"1.Products menu", "2.My orders menu", "0.Logout"};

    private final LoginMenu loginMenu;
    private final OrderService orderService;
    private final ProductService productService;
    private final User currentUser;

    public UserMainMenu(LoginMenu loginMenu,
                        User currentUser,
                        OrderService orderService,
                        ProductService productService) {
        this.loginMenu = loginMenu;
        this.currentUser = currentUser;
        this.orderService = orderService;
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
                case 1 -> new UserProductsMenu(this, orderService, productService).show();
                case 2 -> new UserOrdersMenu(this, orderService).show();
                case 0 -> exit();
            }
        }
    }

    @Override
    public void exit() {
        loginMenu.show();
    }

    User getCurrentUser() {
        return currentUser;
    }
}
