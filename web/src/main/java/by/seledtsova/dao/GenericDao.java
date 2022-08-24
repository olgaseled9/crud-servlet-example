package by.seledtsova.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.seledtsova.exception.DaoException;

/**
 *  Handles  all database operations with object.
 * @param <I> type
 * @param <T> entity
 */
public interface GenericDao<I, T> {

    void add(T entity);

    List<T> getAll();

    void removeById(I id);

    T findById(I id);

    void update(T entity);

    static Long setGeneratedKey(PreparedStatement preparedStatement) {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (!generatedKeys.next()) {
                throw new DaoException("Cannot get id.");
            }
            Long generatedKey = generatedKeys.getLong(1);
            return generatedKey;
        }
        catch (SQLException e) {
            throw new DaoException("Cannot get id.");
        }
    }
}
