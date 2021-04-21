package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private final User user;
    private final Map<Product, Integer> productMap;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;

    public Order(User user) {
        this.user = user;
        this.productMap = new HashMap<>();
        calculateTotalPrice();
        this.orderStatus = OrderStatus.IN_PROGRESS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Map<Product, Integer> getProductMap() {
        return new HashMap<>(productMap);
    }

    public void addProduct(Product product) {

    }

    public void removeProduct(Product product) {

    }

    public void changeProductCount(Product product, int count) {

    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        this.totalPrice = new BigDecimal(0);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
