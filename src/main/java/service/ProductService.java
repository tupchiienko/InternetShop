package service;

import model.Product;
import model.Response;

import java.math.BigDecimal;

public interface ProductService {
    Response<Product> getProductByName(String name);

    Response<Product> getProductById(int id);

    Response<Product> addProduct(Product product);

    Response<Product> removeProduct(int id);

    Response<Product> changeProductName(int id, String name);

    Response<Product> changeProductPrice(int id, BigDecimal price);

    Response<Product> changeProductQuantity(int id, int quantity);
}
