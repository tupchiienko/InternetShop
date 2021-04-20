package dao.impl;

import dao.ProductDao;
import model.Product;

import java.util.Map;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Optional<Product> add(Product product) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getByName(String productName) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> update(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> delete(int id) {
        return Optional.empty();
    }

    @Override
    public Map<Integer, Product> getAllProducts() {
        return null;
    }
}
