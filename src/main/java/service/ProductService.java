package service;

import model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductService {
    Response<Product> getProduct(String name);

    Response<Product> getProduct(int id);

    Response<Map<Integer, Product>> getAllProducts();

    Response<Product> addProduct(Product product);

    Response<Product> deleteProduct(int id);

    Response<Product> changeProductName(int id, String name);

    Response<Product> changeProductPrice(int id, BigDecimal price);

    Response<Product> changeProductQuantity(int id, int quantity);
}
