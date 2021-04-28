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
    public Response<Map<String, Product>> getAllProducts() {
        return null;
    }

    @Override
    public Response<Product> addProduct(Product product) {
        return null;
    }

    @Override
    public Response<Product> deleteProduct(String name) {
        return null;
    }

    @Override
    public Response<Product> changeProductName(String oldName, String newName) {
        return null;
    }

    @Override
    public Response<Product> changeProductPrice(String name, BigDecimal price) {
        return null;
    }

    @Override
    public Response<Product> changeProductQuantity(String name, int quantity) {
        return null;
    }
}
