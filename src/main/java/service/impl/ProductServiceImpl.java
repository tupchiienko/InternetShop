package service.impl;

import model.Product;
import model.Response;
import service.ProductService;

import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {
    @Override
    public Response<Product> getProductByName(String name) {
        return null;
    }

    @Override
    public Response<Product> getProductById(int id) {
        return null;
    }

    @Override
    public Response<Product> addProduct(Product product) {
        return null;
    }

    @Override
    public Response<Product> removeProduct(int id) {
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
