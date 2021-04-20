package dao;

import model.Product;

import java.util.Map;
import java.util.Optional;

public interface ProductDao {
    Optional<Product> add(Product product);

    Optional<Product> getById(int id);

    Optional<Product> getByName(String productName);

    Optional<Product> update(int id);

    Optional<Product> delete(int id);

    Map<Integer, Product> getAllProducts();
}
