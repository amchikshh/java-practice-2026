package Task1.ru.itis.shop.user.infrastructure.persistence;

import Task1.ru.itis.shop.user.domain.User;
import Task1.ru.itis.shop.user.repository.UserRepository;

import java.util.Optional;

public class UserDatabaseRepository implements UserRepository {

    @Override
    public void save(User user) {
        System.out.println("Сохраняем в базу данных...");
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }
}
