package dao.impl;

import dao.ProductDao;
import exception.ProductDataException;
import model.Product;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ProductDaoImpl implements ProductDao {
    private final Map<Integer, Product> productMap;

    public ProductDaoImpl() {
        this.productMap = new TreeMap<>();
    }

    @Override
    public Optional<Product> add(Product product) throws ProductDataException {
        /*
        Перевірити наявність продукту в мапі productMap (containsValue()):
           1. Якщо такий продукт є, викидуємо new ProductDataException з повідомленням,
              що описує виключну ситуацію на англійській мові, наприклад "Product already exist";
           2. Якщо такого продукту немає, присвоюємо id продукту (беремо останній іd з мапи + 1)
              і записуємо його в productMap під цим id (put()), повертаємо (return) доданий продукт в Optional (Optional.of());
         */
        return Optional.empty();
    }

    @Override
    public Optional<Product> getById(int id) {
        /*
        Отримуємо продукт із productMap по id (get()) повертаємо отриманий результат в Optional (Optional.ofNullable());
         */
        return Optional.empty();
    }

    @Override
    public Optional<Product> getByName(String productName) {
        /*
        1. Отримуємо продукт по назві:
                Перебираємо всі значення productMap і порівнюємо назви продуктів з productName,
                якщо співпало, повертаємо Product в Optional (Optional.of());
        2. Повертаємо Optional.empty();
         */
        return Optional.empty();
    }

    @Override
    public Optional<Product> update(int id, Product newProduct) throws ProductDataException {
        /*
        Перевірити наявність продукту з id в мапі productMap (containsKey()):
           1. Якщо такого продукту немає, викидуємо new ProductDataException з повідомленням,
              що описує виключну ситуацію;
           2. Якщо є, оновлюємо значення в мапі (put()), повертаємо в Optional результат операції put();
         */
        return Optional.empty();
    }

    @Override
    public Optional<Product> delete(int id) {
        /*
        Видаляємо продукт з мапи (remove()), повернути в Optional результат операції remove();
         */
        return Optional.empty();
    }

    @Override
    public Map<Integer, Product> getAllProducts() {
        /*
        Повертаємо копію productMap (наприклад, new TreeMap(productMap))
         */
        return null;
    }
}
