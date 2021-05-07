package view.impl;

import model.Order;
import model.OrderStatus;
import model.Product;
import model.User;
import service.OrderService;
import service.ProductService;
import service.Response;
import service.UserService;
import view.Menu;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class AdminMainMenu implements Menu {

    private final String[] items = {"1.Users menu", "2.Order menu", "3.Product menu", "0.Exit"};
    private final String[] itemsForUserMenu = {"1.Block user", "2.Unblock user", "0.Back"};
    private final String[] itemsForOrderMenu = {"1.Change orders status", "0.Back"};
    private final String[] itemsForProductMenu = {"1.Edit product", "2.Add product", "3.Delete product", "0.Back"};
    private final String[] itemsOrderStatus = {"1.Confirm", "2.Unconfirm"};
    private final String[] itemsForEditProduct = {"1.Edit name", "2.Edit price", "3.Edit quantity", "0.Back"};

    private final LoginMenu loginMenu;
    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    private Scanner scanner;

    public AdminMainMenu(LoginMenu loginMenu,
                         UserService userService,
                         OrderService orderService,
                         ProductService productService) {
        this.loginMenu = loginMenu;
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void show() {
        showItems(items);
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 0 -> exit();
            case 1 -> userMenu();
            case 2 -> orderMenu();
            case 3 -> productMenu();
            default -> show();
        }
    }

    @Override
    public void exit() {
        loginMenu.show();
    }

    //block\unblock user
    private void userMenu() {
        showItems(itemsForUserMenu);
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                while (true) {
                    System.out.println("All user: " + userService.getAllUsers().getMessage());
                    System.out.print("Enter username for blocking user: ");
                    Response<User> userResponse = userService.blockUser(scanner.nextLine());
                    System.out.println(userResponse.getMessage());
                    if (userResponse.isSuccessful()) {
                        break;
                    }
                }
                userMenu();
            }
            case 2 -> {
                while (true) {
                    System.out.println("All user: " + userService.getAllUsers().getMessage());
                    System.out.print("Enter username for unblocking user: ");
                    Response<User> userResponse = userService.unblockUser(scanner.nextLine());
                    System.out.println(userResponse.getMessage());
                    if (userResponse.isSuccessful()) {
                        break;
                    }
                }
                userMenu();
            }
            case 0 -> show();
            default -> userMenu();
        }
    }

    //confirm/unconfirm order
    private void orderMenu() {
        showItems(itemsForOrderMenu);
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                while (true) {
                    System.out.println("All user: " + userService.getAllUsers().getMessage());
                    System.out.print("Enter username for check orders: ");
                    String username = scanner.nextLine();
                    Response<User> userResponse = userService.getUser(username);
                    if (!userResponse.isSuccessful()) {
                        continue;
                    }
                    Response<Map<Integer, Order>> orderMapResponse = orderService.getOrdersByUser(userResponse.getValue());
                    System.out.println("'" + username + "' orders: " + orderMapResponse.getMessage());
                    if (!orderMapResponse.isSuccessful()) {
                        continue;
                    }
                    Map<Integer, Order> orderMap = orderMapResponse.getValue();
                    Response<Order> orderStatusResponse;
                    while (true) {
                        System.out.print("Choose order id to change: ");
                        int orderId = scanner.nextInt();
                        Order order = orderMap.get(orderId);
                        if (order == null) {
                            System.out.println("Order '" + orderId + "' does not exist");
                            continue;
                        }
                        OrderStatus newStatus;
                        while (true) {
                            showItems(itemsOrderStatus);
                            System.out.print("Choose status for order: ");
                            int orderStatusIndex = scanner.nextInt();
                            if (orderStatusIndex == 1) {
                                newStatus = OrderStatus.CONFIRMED;
                            } else if (orderStatusIndex == 2) {
                                newStatus = OrderStatus.UNCONFIRMED;
                            } else {
                                System.out.println("Wrong status number");
                                continue;
                            }
                            break;
                        }
                        orderStatusResponse = orderService.changeOrderStatus(orderId, newStatus);
                        System.out.println(orderStatusResponse.getMessage());
                        if (orderStatusResponse.isSuccessful()) {
                            break;
                        }
                    }
                    break;
                }
                orderMenu();
            }
            case 0 -> show();
            default -> orderMenu();
        }
    }

    //Edit existing product details. Add new product.
    private void productMenu() {
        showItems(itemsForProductMenu);
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("All products: " + productService.getAllProducts().getValue());
                System.out.print("Enter product name for edit: ");
                String productName = scanner.nextLine();
                while (true) {
                    showItems(itemsForEditProduct);
                    int chosenItem = scanner.nextInt();
                    Response<Product> changeProductResponse;
                    switch (chosenItem) {
                        case 1 -> {
                            System.out.print("Enter new product name: ");
                            String newProductName = scanner.nextLine();
                            changeProductResponse = productService.changeProductName(productName, newProductName);
                            System.out.println(changeProductResponse.getMessage());
                            continue;
                        }
                        case 2 -> {
                            System.out.print("Enter new product price: ");
                            double newProductPrice = scanner.nextDouble();
                            changeProductResponse = productService.changeProductPrice(productName, new BigDecimal(newProductPrice));
                            System.out.println(changeProductResponse.getMessage());
                            continue;
                        }
                        case 3 -> {
                            System.out.println("Enter new product quantity: ");
                            int newProductQuantity = scanner.nextInt();
                            changeProductResponse = productService.changeProductQuantity(productName, newProductQuantity);
                            System.out.println(changeProductResponse.getMessage());
                            continue;
                        }
                    }
                    break;
                }
            }
            case 2 -> {
                while (true) {
                    System.out.print("Enter new product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter product quantity: ");
                    int quantity = scanner.nextInt();
                    try {
                        Product newProduct = new Product(name, new BigDecimal(price), quantity);
                        Response<Product> productResponse = productService.addProduct(newProduct);
                        System.out.println(productResponse.getMessage());
                        if (productResponse.isSuccessful()) {
                            break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            case 3 -> {
                while (true) {
                    System.out.print("Enter product name for delete: ");
                    Response<Product> productDeleteResponse = productService.deleteProduct(scanner.nextLine());
                    System.out.println(productDeleteResponse.getMessage());
                    if (productDeleteResponse.isSuccessful()) {
                        break;
                    }
                }
            }
            case 0 -> show();
            default -> productMenu();
        }
    }
}
