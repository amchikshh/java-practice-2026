package Task4.ru.itis.shop.user.infrastructure.persistence;

import Task4.ru.itis.shop.user.domain.User;
import Task4.ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;

public class UserRepositoryJdbcImpl implements UserRepository {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/amchikshh";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "1234";

    @Override
    public void save(User user){
        String firstName = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        String profileDescription = user.getProfileDescription();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "insert into account (firstName, email, password, profileDescription) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, profileDescription);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Успешно!");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void updateUserDescription(User user) {
    }

    @Override
    public List<User> findAll() {
        List<User> toPrint = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")){
                    while (resultSet.next()) {
                        User user = new User(resultSet.getString("id"),
                                resultSet.getString("firstName"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("profileDescription"));
                        toPrint.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return toPrint;
    }
}
