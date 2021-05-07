package service.impl;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import model.Order;
import model.OrderStatus;
import model.Product;
import model.User;
import service.OrderService;
import service.Response;

import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl() {
        this.orderDao = new OrderDaoImpl();
    }

    @Override
    public Response<Map<Integer, Order>> getOrdersByUser(User user) {
        Map<Integer, Order> byUserMap = orderDao.getByUser(user);
        if (byUserMap.isEmpty()) {
            return new Response<>(byUserMap, false,
                    "User has no orders");
        }
        return new Response<>(byUserMap, true,
                "User has orders with id '" + byUserMap.keySet() + "'");
    }

    @Override
    public Response<Map<Integer, Order>> getOrdersByOrderStatus(OrderStatus orderStatus) {
        Map<Integer, Order> byStatusMap = orderDao.getByStatus(orderStatus);
        if (byStatusMap.isEmpty()) {
            return new Response<>(byStatusMap, false,
                    "No orders " + orderStatus);
        }
        return new Response<>(byStatusMap, true,
                "Success");
    }

    @Override
    public Response<Order> addOrder(Order order) {
        Optional<Order> orderOptional = orderDao.getById(order.getId());
        if (orderOptional.isEmpty()) {
            orderDao.add(order);
            return new Response<>(order, true,
                    "Order '" + order.getId() + "' successfully created");
        }
        return new Response<>(null, false,
                "Order '" + order.getId() + "' already exist");
    }

    @Override
    public Response<Order> changeOrderStatus(int id, OrderStatus orderStatus) {
        Optional<Order> orderOptional = orderDao.getById(id);
        if (orderOptional.isPresent()) {
            Order orderToChange = orderOptional.get();
            orderToChange.setOrderStatus(orderStatus);
            orderDao.update(id, orderToChange);
            return new Response<>(orderToChange, true,
                    "Order '" + id + "' successfully updated");
        }
        return new Response<>(null, false,
                "Order with id `" + id + " does not exist");
    }

    @Override
    public Response<Order> addProductToOrder(int id, Product product, int quantity) {
        if (quantity < 0) {
            return new Response<>(null, false, "Quantity can not be lower than zero");
        }
        Optional<Order> orderOptional = orderDao.getById(id);
        if (orderOptional.isPresent()) {
            Order orderToChange = orderOptional.get();
            orderToChange.addProduct(product, quantity);
            orderDao.update(id, orderToChange);
            return new Response<>(orderToChange, true,
                    "Order '" + id + "' successfully updated");
        }
        return new Response<>(null, false,
                "Order with id `" + id + " does not exist");
    }

    @Override
    public Response<Order> removeProductFormOrder(int id, Product product) {
        Optional<Order> orderOptional = orderDao.getById(id);
        if (orderOptional.isPresent()) {
            Order orderToChange = orderOptional.get();
            orderToChange.removeProduct(product);
            orderDao.update(id, orderToChange);
            return new Response<>(orderToChange, true,
                    "Order '" + id + "' successfully updated");
        }
        return new Response<>(null, false,
                "Order with id `" + id + " does not exist");
    }
}
