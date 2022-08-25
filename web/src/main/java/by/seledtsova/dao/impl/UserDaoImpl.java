package by.seledtsova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import by.seledtsova.connection.ConnectionToDatabase;
import by.seledtsova.dao.GenericDao;
import by.seledtsova.dao.UserDao;
import by.seledtsova.exception.DaoException;
import by.seledtsova.model.Role;
import by.seledtsova.model.RoleValue;
import by.seledtsova.model.User;

/**
 * Is an implementation of the {@link UserDao} interface.
 */
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());

    private static final String SELECT_ALL_FROM_USERS_TABLE = "SELECT user_id, first_name, last_name," +
        " login, password, r.role_id, r.name FROM users LEFT JOIN roles r on r.role_id = users.role_id";
    private static final String DELETE_FROM_USERS_BY_ID = "DELETE FROM users WHERE user_id = ?";
    private static final String ADD_NEW_USER = "INSERT INTO users (first_name, last_name, login, " +
        "password, role_id) VALUES (?,?,?,?,?)";
    private static final String SELECT_USER_BY_ID = "SELECT user_id, first_name, last_name, login, password," +
        "r.role_id, r.name FROM users LEFT JOIN roles r on r.role_id = users.role_id WHERE user_id=?";
    private static final String UPDATE_USER = "UPDATE users SET first_name=?, last_name=?, login=?, " +
        "password=?, role_id=? WHERE user_id = ?";


    @Override
    public void add(User user) {
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER,
                 Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getRole().getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Not able to save user : " + user);
            }
            user.setUserId(GenericDao.setGeneratedKey(preparedStatement));
            LOGGER.info("Successfully saved user : " + user);
        }
        catch (SQLException e) {
            throw new DaoException("Not able to save user : " + user, e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionToDatabase.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USERS_TABLE)) {
            while (resultSet.next()) {
                users.add(setFields(resultSet));
            }
            LOGGER.info("Successfully get all users.");
        }
        catch (SQLException e) {
            throw new DaoException("Not able to get all users.", e);
        }
        return users;
    }

    @Override
    public void removeById(Long id) {
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_USERS_BY_ID)) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Not able to delete user with id = " + id);
            }
            LOGGER.info("Successfully deleted user with id = " + id);
        }
        catch (SQLException e) {
            throw new DaoException("Not able to delete user with id = " + id, e);
        }
    }

    @Override
    public User findById(Long id) {
        User user;
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new DaoException("User not found with id = " + id);
                }
                user = setFields(resultSet);
                LOGGER.info("User successfully found with id = " + id + " : " + user);
            }
        }
        catch (SQLException e) {
            throw new DaoException("User not found with id = " + id, e);
        }
        return user;
    }

    @Override
    public void update(User user) {
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setLong(5, user.getRole().getId());
            preparedStatement.setLong(6, user.getUserId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Not able to update user : " + user);
            }
            LOGGER.info("User successfully updated : " + user);
        }
        catch (SQLException e) {
            throw new DaoException("Not able to update user : " + user, e);
        }
    }

    private User setFields(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong("user_id"));
        user.setFirstname(resultSet.getString("first_name"));
        user.setLastname(resultSet.getString("last_name"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        Role role = new Role();
        role.setId(Long.valueOf(resultSet.getString("role_id")));
        role.setName(RoleValue.valueOf(resultSet.getString("name")));
        user.setRole(role);
        return user;
    }
}
