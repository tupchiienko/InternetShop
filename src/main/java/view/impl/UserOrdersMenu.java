package view.impl;

import model.Order;
import service.OrderService;
import service.Response;
import view.Menu;

import java.util.Map;
import java.util.Scanner;

public class UserOrdersMenu implements Menu {
    private final String[] items = {"1.Order list", "0.Back to main menu"};

    private final UserMainMenu userMainMenu;
    private final OrderService orderService;

    public UserOrdersMenu(UserMainMenu userMainMenu,
                          OrderService orderService) {
        this.userMainMenu = userMainMenu;
        this.orderService = orderService;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        //noinspection InfiniteLoopStatement
        while (true) {
            showItems(items);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> showOrderList();
                case 0 -> exit();
            }
        }
    }

    private void showOrderList() {
        Response<Map<Integer, Order>> ordersByUserResponse = orderService.getOrdersByUser(userMainMenu.getCurrentUser());
        if (!ordersByUserResponse.isSuccessful()) {
            System.out.println(ordersByUserResponse.getMessage());
            return;
        }
        System.out.println("Order list:");
        ordersByUserResponse.getValue().values().forEach(System.out::println);
    }

    @Override
    public void exit() {
        userMainMenu.show();
    }
}
