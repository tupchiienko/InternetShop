package view.impl;

import model.Order;
import model.OrderStatus;
import model.Product;
import service.OrderService;
import service.ProductService;
import service.Response;
import service.UserService;
import view.Menu;

import java.util.*;

public class UserProductsMenu implements Menu {
    private final String[] items = {"1.Show product list", "2.Search product",
            "3.Add product to order", "4.Confirm order", "0.Back to main menu"};
    private final String[] checkoutItems = {"1.Remove product", "2.Change product count", "3.Confirm order", "0.Back"};

    private final UserMainMenu userMainMenu;
    private final OrderService orderService;
    private final ProductService productService;

    public UserProductsMenu(UserMainMenu userMainMenu,
                            OrderService orderService,
                            ProductService productService) {
        this.userMainMenu = userMainMenu;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        //noinspection InfiniteLoopStatement
        while (true) {
            showItems(items);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> showProductList();
                case 2 -> searchProduct(scanner);
                case 3 -> addProductToOrder(scanner);
                case 4 -> orderCheckout(scanner);
                case 0 -> exit();
            }
        }
    }

    private void showProductList() {
        Response<Map<String, Product>> allProductsMapResponse = productService.getAllProducts();
        if (!allProductsMapResponse.isSuccessful()) {
            System.out.println(allProductsMapResponse.getMessage());
            return;
        }
        Map<String, Product> allProductsMap = allProductsMapResponse.getValue();
        System.out.println("Product list:");
        System.out.println("-".repeat(50));
        allProductsMap.values().forEach(System.out::println);
        System.out.println("-".repeat(50));
    }

    private void searchProduct(Scanner scanner) {
        List<Product> findProductList = new ArrayList<>();
        System.out.print("Enter product name for search: ");
        String productName = scanner.nextLine();
        Response<Map<String, Product>> allProductsResponse = productService.getAllProducts();
        if (allProductsResponse.isSuccessful()) {
            Collection<Product> productCollection = allProductsResponse.getValue().values();
            for (Product product : productCollection) {
                if (product.getName().toLowerCase(Locale.ROOT).contains(productName.toLowerCase(Locale.ROOT))) {
                    findProductList.add(product);
                }
            }
        }
        if (findProductList.isEmpty()) {
            System.out.println("Product does not exist");
        } else {
            findProductList.forEach(System.out::println);
        }
    }

    private void addProductToOrder(Scanner scanner) {
        Response<Map<Integer, Order>> ordersByUserResponse = orderService.getOrdersByUser(userMainMenu.getCurrentUser());
        Collection<Order> orderCollection = ordersByUserResponse.getValue().values();
        int orderId = orderCollection.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.IN_PROGRESS)
                .findFirst()
                .orElseGet(() -> orderService.addOrder(new Order(userMainMenu.getCurrentUser())).getValue())
                .getId();
        while (true) {
            showProductList();
            System.out.print("Enter product name: ");
            String productName = scanner.nextLine();
            Response<Product> productResponse = productService.getProduct(productName);
            if (!productResponse.isSuccessful()) {
                System.out.println(productResponse.getMessage());
                continue;
            }
            Product product = productResponse.getValue();
            System.out.print("Enter quantity for '" + product.getName() + "': ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            Response<Order> addProductResponse = orderService
                    .addProductToOrder(orderId, productResponse.getValue(), quantity);
            System.out.println(addProductResponse.getMessage());
            if (addProductResponse.isSuccessful()) {
                break;
            }
        }
    }

    private void orderCheckout(Scanner scanner) {
        Response<Map<Integer, Order>> ordersByUserResponse = orderService.getOrdersByUser(userMainMenu.getCurrentUser());
        Collection<Order> orderCollection = ordersByUserResponse.getValue().values();
        Optional<Order> inProgressOptional = orderCollection.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.IN_PROGRESS)
                .findFirst();
        if (inProgressOptional.isEmpty()) {
            System.out.println("You have no active orders. New order creates in 'Add product to order'");
            return;
        }
        Order order = inProgressOptional.get();
        if (order.getProductMap().isEmpty()) {
            System.out.println("Your order is empty");
            return;
        }
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.println(order);
            showItems(checkoutItems);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    while (true) {
                        List<Product> productList = new ArrayList<>(order.getProductMap().keySet());
                        for (int i = 0; i < productList.size(); i++) {
                            System.out.println((i + 1) + "." + productList.get(i));
                        }
                        System.out.print("Choose product number for remove: ");
                        int productNumber = scanner.nextInt();
                        scanner.nextLine();
                        Product productToRemove;
                        try {
                            productToRemove = productList.get(productNumber - 1);
                        } catch (IndexOutOfBoundsException exception) {
                            System.out.println("Incorrect product number");
                            continue;
                        }
                        Response<Order> orderResponse = orderService.removeProductFormOrder(order.getId(), productToRemove);
                        System.out.println(orderResponse.getMessage());
                        if (orderResponse.isSuccessful()) {
                            order = orderResponse.getValue();
                        }
                        break;
                    }
                }
                case 2 -> {
                    while (true) {
                        List<Product> productList = new ArrayList<>(order.getProductMap().keySet());
                        for (int i = 0; i < productList.size(); i++) {
                            System.out.println((i + 1) + "." + productList.get(i));
                        }
                        System.out.print("Choose product number for change count: ");
                        int productNumber = scanner.nextInt();
                        scanner.nextLine();
                        Product productToChangeCount;
                        try {
                            productToChangeCount = productList.get(productNumber - 1);
                        } catch (IndexOutOfBoundsException exception) {
                            System.out.println("Incorrect product number");
                            continue;
                        }
                        System.out.print("Enter new quantity: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        Response<Order> orderResponse =
                                orderService.addProductToOrder(order.getId(), productToChangeCount, quantity);
                        System.out.println(orderResponse.getMessage());
                        if (orderResponse.isSuccessful()) {
                            order = orderResponse.getValue();
                        }
                        break;
                    }
                }
                case 3 -> {
                    Response<Order> changeOrderStatusResponse =
                            orderService.changeOrderStatus(order.getId(), OrderStatus.AWAITING_CONFIRMATION);
                    System.out.println(changeOrderStatusResponse.getMessage());
                    if (changeOrderStatusResponse.isSuccessful()) {
                        order = changeOrderStatusResponse.getValue();
                        show();
                    }
                }
                case 0 -> show();
            }
        }
    }

    @Override
    public void exit() {
        userMainMenu.show();
    }
}
