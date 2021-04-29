package dao.impl;

import dao.UserDao;
import exception.UserDataException;
import model.User;
import model.UserRole;

import java.util.Map;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public Optional<User> add(User user) {
        /*
        Перевірити наявність користувача в мапі userMap:
           1. Якщо такий користувач є, викинути new UserDataException з повідомленням,
              що описує виключну ситуацію на англійській мові, наприклад "User already exist";
           2. Якщо такого користувача немає, записати його в userMap під ключем username;
           3. Повернути доданого користувача в Optional;
         */
        return Optional.empty();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        /*
        Отримати користувача із userMap по username, повернути отриманий результат в Optional;
         */
        return Optional.empty();
    }

    @Override
    public Optional<User> update(String username, User newUser) {
        /*
        Перевірити наявність користувача з username в мапі userMap:
           1. Якщо такого користувача немає, викинути new UserDataException з повідомленням,
              що описує виключну ситуацію;
           2. Якщо є, оновлюємо значення в мапі, повертаємо в Optional результат операції put();
         */
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(String username) {
        /*
        Видалити користувача з мапи, повернути в Optional результат операції remove();
         */
        return Optional.empty();
    }

    @Override
    public Map<String, User> getByRole(UserRole userRole) {
        /*
        Повернути мапу (TreeMap) користувачів з роллю userRole
         */
        return null;
    }

    @Override
    public Map<String, User> getAllUsers() {
        /*
        Повернути копію userMap
         */
        return null;
    }
}
