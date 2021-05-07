package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {
    private static int idCounter;

    private final int id;
    private final User user;
    private final Map<Product, Integer> productMap;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;

    public Order(User user) {
        this.id = ++idCounter;
        this.user = user;
        this.productMap = new HashMap<>();
        this.totalPrice = new BigDecimal(0);
        this.orderStatus = OrderStatus.IN_PROGRESS;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Map<Product, Integer> getProductMap() {
        return new HashMap<>(productMap);
    }

    public void addProduct(Product product, int count) {
        productMap.put(product, count);
        totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(count)));
    }

    public void removeProduct(Product product) {
        totalPrice = totalPrice.subtract(product.getPrice().multiply(new BigDecimal(productMap.get(product))));
        productMap.remove(product);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
