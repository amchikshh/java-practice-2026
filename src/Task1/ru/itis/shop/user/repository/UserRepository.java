package Task1.ru.itis.shop.user.repository;

import Task1.ru.itis.shop.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(String id);
}
