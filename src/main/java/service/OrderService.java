package service;

import model.Order;
import model.OrderStatus;
import model.Product;
import model.User;

import java.util.Map;

public interface OrderService {
    Response<Map<Integer, Order>> getOrdersByUser(User user);

    Response<Map<Integer, Order>> getOrdersByOrderStatus(OrderStatus orderStatus);

    Response<Order> addOrder(Order order);

    Response<Order> changeOrderStatus(int id, OrderStatus orderStatus);

    Response<Order> addProductToOrder(int id, Product product, int quantity);

    Response<Order> removeProductFormOrder(int id, Product product);
}
