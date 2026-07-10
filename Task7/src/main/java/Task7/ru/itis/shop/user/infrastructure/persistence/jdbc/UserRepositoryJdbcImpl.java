package Task7.ru.itis.shop.user.infrastructure.persistence.jdbc;

import Task7.ru.itis.shop.infrastructure.persistence.jdbc.RowMapper;
import Task7.ru.itis.shop.user.domain.User;
import Task7.ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final DataSource dataSource;

    private final RowMapper<User> userRowMapper = row -> new User(
            row.getInt("id"),
            row.getString("firstname"),
            row.getString("email"),
            row.getString("password"),
            row.getString("profileDescription")
    );

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        String firstName = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        String profileDescription = user.getProfileDescription();
        try (Connection connection = dataSource.getConnection()) {
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
        try (Connection connecton = dataSource.getConnection()) {
            String sql = "select * from account where email = ?";
            PreparedStatement statement = connecton.prepareStatement(sql);
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = userRowMapper.mapRow(resultSet);
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from account where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = userRowMapper.mapRow(resultSet);
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void updateUserDescription(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "update account set profileDescription = ? where email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getProfileDescription());
            preparedStatement.setString(2, user.getEmail());

            int rows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")) {
                    while (resultSet.next()) {
                        users.add(userRowMapper.mapRow(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }

    @Override
    public List<User> findAllByProfileDescription(String description) {
        List<User> users = new ArrayList<>();
        try (Connection connecton = dataSource.getConnection()) {
            String sql = "select * from account where profileDescription = ?";
            PreparedStatement statement = connecton.prepareStatement(sql);
            statement.setString(1,description);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        users.add(userRowMapper.mapRow(resultSet));
                    }
                }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }
}
