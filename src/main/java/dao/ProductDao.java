package dao;

import model.Product;

import java.util.Optional;

public interface ProductDao {
    Optional<Product> addProduct(Product product);

    Optional<Product> getProduct(int id);

    Optional<Product> getProduct(String productName);

    Optional<Product> updateProduct(int id);

    Optional<Product> deleteProduct(int id);
}
