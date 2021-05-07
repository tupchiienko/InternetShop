package dao;

import model.Order;
import model.OrderStatus;
import model.User;

import java.util.Map;
import java.util.Optional;

public interface OrderDao {
    Optional<Order> add(Order order);

    Optional<Order> getById(int id);

    Optional<Order> update(int id, Order newOrder);

    Optional<Order> delete(int id);

    Map<Integer, Order> getByUser(User user);

    Map<Integer, Order> getByStatus(OrderStatus orderStatus);
}
