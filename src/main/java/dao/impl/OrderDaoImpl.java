package dao.impl;

import dao.OrderDao;
import model.Order;
import model.OrderStatus;
import model.User;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.stream.Collectors.toMap;

public class OrderDaoImpl implements OrderDao {
    private final Map<Integer, Order> orderMap;

    public OrderDaoImpl() {
        orderMap = new TreeMap<>();
    }

    @Override
    public Optional<Order> add(Order order) {
        return Optional.ofNullable(orderMap.put(order.getId(), order));
    }

    @Override
    public Optional<Order> getById(int id) {
        return Optional.ofNullable(orderMap.get(id));
    }

    @Override
    public Optional<Order> update(int id, Order newOrder) {
        Optional<Order> deletedOrder = delete(id);
        if (deletedOrder.isPresent()) {
            add(newOrder);
        }
        return deletedOrder;
    }

    @Override
    public Optional<Order> delete(int id) {
        return Optional.ofNullable(orderMap.remove(id));
    }

    @Override
    public Map<Integer, Order> getByUser(User user) {
        return orderMap.values().stream()
                .filter(order -> order.getUser().equals(user))
                .collect(toMap(Order::getId, Order -> Order));
    }

    @Override
    public Map<Integer, Order> getByStatus(OrderStatus orderStatus) {
        return orderMap.values().stream()
                .filter(order -> order.getOrderStatus() == orderStatus)
                .collect(toMap(Order::getId, order -> order));
    }
}
