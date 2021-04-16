package dao.impl;

import dao.ProductDao;
import model.Product;

import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Optional<Product> addProduct(Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getProduct(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getProduct(String productName) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> updateProduct(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteProduct(int id) {
        return Optional.empty();
    }
}
