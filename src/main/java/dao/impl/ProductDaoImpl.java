package dao.impl;

import dao.ProductDao;
import model.Product;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ProductDaoImpl implements ProductDao {
    private final Map<String, Product> productMap;

    public ProductDaoImpl() {
        this.productMap = new TreeMap<>();
    }

    @Override
    public Optional<Product> add(Product product) {
        return Optional.ofNullable(productMap.put(product.getName(), product));
    }

    @Override
    public Optional<Product> getByName(String name) {
        return Optional.ofNullable(productMap.get(name));
    }

    @Override
    public Optional<Product> update(String name, Product newProduct) {
        return Optional.ofNullable(productMap.replace(name, newProduct));
    }

    @Override
    public Optional<Product> delete(String name) {
        return Optional.ofNullable(productMap.remove(name));
    }

    @Override
    public Map<String, Product> getAllProducts() {
        return new TreeMap<>(productMap);
    }
}
