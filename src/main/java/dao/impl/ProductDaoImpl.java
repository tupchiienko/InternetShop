package dao.impl;

import dao.ProductDao;
import model.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ProductDaoImpl implements ProductDao {
    private final Map<String, Product> productMap;

    public ProductDaoImpl() {
        this.productMap = new TreeMap<>();
        add(new Product("Cheese", new BigDecimal("20.00"), 53));
        add(new Product("Beer", new BigDecimal("3.50"), 78));
        add(new Product("Choco", new BigDecimal("5.00"), 89));
        add(new Product("Wine", new BigDecimal("200.00"), 40));
        add(new Product("Tea", new BigDecimal("10.00"), 16));
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
        Optional<Product> deletedProduct = delete(name);
        if (deletedProduct.isPresent()) {
            add(newProduct);
        }
        return deletedProduct;
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
