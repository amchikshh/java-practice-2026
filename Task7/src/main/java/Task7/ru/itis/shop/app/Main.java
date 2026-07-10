package Task7.ru.itis.shop.app;

import Task7.ru.itis.shop.infrastructure.persistence.jdbc.DriverManagerDataSource;
import Task7.ru.itis.shop.user.api.UserConsoleOperations;
import Task7.ru.itis.shop.user.application.UserService;
import Task7.ru.itis.shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import Task7.ru.itis.shop.user.repository.UserRepository;
import Task7.ru.itis.shop.util.PropertiesReader;

import javax.sql.DataSource;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        PropertiesReader propertiesReader = new PropertiesReader("application.properties");
        Properties properties = propertiesReader.loadProperties();
        DataSource dataSource  = new DriverManagerDataSource(properties.getProperty("db.url"),
                properties.getProperty("db.username"), properties.getProperty("db.password"));
        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserService(userRepository);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}
