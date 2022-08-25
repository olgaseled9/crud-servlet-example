package by.seledtsova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import by.seledtsova.connection.ConnectionToDatabase;
import by.seledtsova.dao.RoleDao;
import by.seledtsova.exception.DaoException;
import by.seledtsova.model.Role;
import by.seledtsova.model.RoleValue;

/**
 * Is an implementation of the {@link RoleDao} interface.
 */
public class RoleDaoImpl implements RoleDao {

    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class.getName());
    private static final String FIND_ROLE_BY_NAME = "SELECT role_id, name FROM roles WHERE name = ?";

    @Override
    public Role findByName(RoleValue roleValue) throws DaoException {
        Role role;
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROLE_BY_NAME)) {
            preparedStatement.setString(1, roleValue.name());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new DaoException("Can't find role with name : " + roleValue.name());
                }
                role = setFields(resultSet);
                LOGGER.info("Successfully find role with name : " + roleValue.name());
            }
        }
        catch (SQLException e) {
            throw new DaoException("Can't find role with name : " + roleValue.name(), e);
        }
        return role;
    }

    private Role setFields(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong("role_id"));
        RoleValue roleValue = RoleValue.valueOf(resultSet.getString("name"));
        role.setName(roleValue);
        return role;
    }
}
