package service.impl;

import model.Product;
import service.Response;
import service.ProductService;

import java.math.BigDecimal;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    @Override
    public Response<Product> getProduct(String name) {
        return null;
    }

    @Override
    public Response<Product> getProduct(int id) {
        return null;
    }

    @Override
    public Response<Map<Integer, Product>> getAllProducts() {
        return null;
    }

    @Override
    public Response<Product> addProduct(Product product) {
        return null;
    }

    @Override
    public Response<Product> deleteProduct(int id) {
        return null;
    }

    @Override
    public Response<Product> changeProductName(int id, String name) {
        return null;
    }

    @Override
    public Response<Product> changeProductPrice(int id, BigDecimal price) {
        return null;
    }

    @Override
    public Response<Product> changeProductQuantity(int id, int quantity) {
        return null;
    }
}
