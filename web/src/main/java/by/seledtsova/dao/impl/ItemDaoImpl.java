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
import by.seledtsova.dao.ItemDao;
import by.seledtsova.exception.DaoException;
import by.seledtsova.model.Item;


/**
 * Implementation of {@link ItemDao} interface.
 */
public class ItemDaoImpl implements ItemDao {

    private static final Logger LOGGER = Logger.getLogger(ItemDaoImpl.class.getName());

    private static final String SELECT_ALL_FROM_ITEM_TABLE = "SELECT item_id, name, description FROM item";
    private static final String DELETE_FROM_ITEM_BY_ID = "DELETE FROM item WHERE item_id = ?";
    private static final String ADD_NEW_ITEM = "INSERT INTO item (name, description) VALUES (?,?)";
    private static final String SELECT_FROM_ITEM_TABLE_ITEM_BY_ID = "SELECT item_id, name, description " +
        "FROM item WHERE item_id=?";
    private static final String UPDATE_ITEM = "UPDATE item SET name=?, description=? WHERE item_id = ?";

    @Override
    public List<Item> getAll() throws DaoException {
        List<Item> items = new ArrayList<>();
        try (Connection connection = ConnectionToDatabase.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_ITEM_TABLE)) {
            while (resultSet.next()) {
                items.add(setFields(resultSet));
            }
            LOGGER.info("Successfully get all items.");
        }
        catch (SQLException e) {
            throw new DaoException("Not able to get all items.", e);
        }
        return items;
    }

    @Override
    public Item findById(Long id) throws DaoException {
        Item item;
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ITEM_TABLE_ITEM_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new DaoException("Item not found with id = " + id);
                }
                item = setFields(resultSet);
                LOGGER.info("Item successfully found with id = " + id + " : " + item);
            }
        }
        catch (SQLException e) {
            throw new DaoException("Item not found with id = " + id, e);
        }
        return item;
    }

    @Override
    public void update(Item item) throws DaoException {
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setLong(3, item.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Not able to update item : " + item);
            }
            LOGGER.info("Item successfully updated : " + item);
        }
        catch (SQLException e) {
            throw new DaoException("Not able to update item : " + item, e);
        }
    }

    @Override
    public void removeById(Long id) throws DaoException {
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_ITEM_BY_ID)) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Not able to delete item with id = " + id);
            }
            LOGGER.info("Successfully deleted item with id = " + id);
        }
        catch (SQLException e) {
            throw new DaoException("Not able to delete item with id = " + id, e);
        }
    }

    @Override
    public void add(Item item) {
        try (Connection connection = ConnectionToDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ITEM,
                 Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            if (preparedStatement.executeUpdate() == 0) {
                throw new DaoException("Not able to save item : " + item);
            }
            item.setId(GenericDao.setGeneratedKey(preparedStatement));
            LOGGER.info("Successfully saved item : " + item);
        }
        catch (SQLException e) {
            throw new DaoException("Not able to save item : " + item, e);
        }
    }

    private Item setFields(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getLong("item_id"));
        item.setName(resultSet.getString("name"));
        item.setDescription(resultSet.getString("description"));
        return item;
    }
}
