package Task4.ru.itis.shop.app;

import Task4.ru.itis.shop.user.api.UserConsoleOperations;
import Task4.ru.itis.shop.user.application.UserService;
import Task4.ru.itis.shop.user.infrastructure.persistence.UserFileRepository;
import Task4.ru.itis.shop.user.infrastructure.persistence.UserMapper;
import Task4.ru.itis.shop.user.infrastructure.persistence.UserRepositoryJdbcImpl;

public class Main {
    public static void main(String[] args) {
        UserFileRepository userFileRepository = new UserFileRepository("src/users.txt", new UserMapper());
        UserRepositoryJdbcImpl userRepositoryJdbc = new UserRepositoryJdbcImpl();
        UserService userService = new UserService(userFileRepository);
        UserService userService1 = new UserService(userRepositoryJdbc);
        UserConsoleOperations operations = new UserConsoleOperations(userService1);

        while (true) {
            operations.showMenu();
        }
    }
}
