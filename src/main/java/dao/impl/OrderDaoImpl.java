package dao.impl;

import dao.OrderDao;
import model.Order;
import model.User;

import java.util.Map;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Optional<Order> add(Order order) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> getById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> update(int id, Order newOrder) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> delete(int id) {
        return Optional.empty();
    }

    @Override
    public Map<Integer, Order> getByUser(User user) {
        return null;
    }
}
