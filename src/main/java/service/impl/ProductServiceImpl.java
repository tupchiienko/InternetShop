package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;
import service.Response;

import java.util.Map;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl() {
        this.productDao = new ProductDaoImpl();
    }

    @Override
    public Response<Product> getProduct(String name) {
        name = name.strip();
        Optional<Product> product = productDao.getByName(name);
        return product
                .map(value -> new Response<>(value, true, value.toString()))
                .orElse(new Response<>(null, false,
                        "Product '" + name + "' does not exist."));
    }

    @Override
    public Response<Map<String, Product>> getAllProducts() {
        Map<String, Product> productMap = productDao.getAllProducts();
        if (productMap.isEmpty()) {
            return new Response<>(null, false, "Product database is empty.");
        }
        return new Response<>(productMap, true, productMap.values().toString());
    }

    @Override
    public Response<Product> addProduct(Product product) {
        Optional<Product> productByName = productDao.getByName(product.getName());
        if (productByName.isPresent()) {
            return new Response<>(productByName.get(), false,
                    "Product with name '" + product.getName() + "' already exist.");
        }
        productDao.add(product);
        return new Response<>(product, true,
                "Product '" + product.getName() + "' added to database");
    }

    @Override
    public Response<Product> deleteProduct(String name) {
        name = name.strip();
        Optional<Product> deletedProduct = productDao.delete(name);
        return deletedProduct
                .map(value -> new Response<>(value, true,
                        "Product '" + value.getName() + "' deleted."))
                .orElse(new Response<>(null, false,
                        "Product '" + name + "' does not exist."));
    }

    @Override
    public Response<Product> updateProduct(String name, Product newProduct) {
        name = name.strip();
        Optional<Product> updatedProduct = productDao.update(name, newProduct);
        return updatedProduct
                .map(product -> new Response<>(product, true,
                        "Product '" + product + "' updated - " + newProduct)).
                orElse(new Response<>(null, false,
                        "Product '" + name + "' does not exist."));
    }
}
