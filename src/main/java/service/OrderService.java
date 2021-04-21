package service;

import model.Order;
import model.OrderStatus;
import model.Product;
import model.User;

import java.util.Map;

public interface OrderService {
    Response<Order> getOrder(int id);

    Response<Order> deleteOrder(int id);

    Response<Map<Integer, Order>> getOrdersByUser(User user);

    Response<Order> addOrder(Order order);

    Response<Order> changeOrderStatus(int id, OrderStatus orderStatus);

    Response<Order> addProductToOrder(int id, Product product);

    Response<Order> removeProductFormOrder(int id, Product product);
}
