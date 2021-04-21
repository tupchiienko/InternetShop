package service.impl;

import model.Order;
import model.OrderStatus;
import model.Product;
import model.User;
import service.OrderService;
import service.Response;

import java.util.Map;

public class OrderServiceImpl implements OrderService {
    @Override
    public Response<Order> getOrder(int id) {
        return null;
    }

    @Override
    public Response<Order> deleteOrder(int id) {
        return null;
    }

    @Override
    public Response<Map<Integer, Order>> getOrdersByUser(User user) {
        return null;
    }

    @Override
    public Response<Order> addOrder(Order order) {
        return null;
    }

    @Override
    public Response<Order> changeOrderStatus(int id, OrderStatus orderStatus) {
        return null;
    }

    @Override
    public Response<Order> addProductToOrder(int id, Product product) {
        return null;
    }

    @Override
    public Response<Order> removeProductFormOrder(int id, Product product) {
        return null;
    }
}
