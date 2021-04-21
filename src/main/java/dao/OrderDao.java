package dao;

import exception.OrderDataException;
import model.Order;
import model.User;

import java.util.Map;
import java.util.Optional;

public interface OrderDao {
    Optional<Order> add(Order order);

    Optional<Order> getById(int id);

    Optional<Order> update(int id, Order newOrder) throws OrderDataException;

    Optional<Order> delete(int id);

    Map<Integer, Order> getByUser(User user);
}
