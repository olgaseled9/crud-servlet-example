package by.seledtsova.dao;


import by.seledtsova.exception.DaoException;
import by.seledtsova.model.Role;
import by.seledtsova.model.RoleValue;

/**
 * Finds object status by his name  {@link Role}.
 */
public interface RoleDao {

    /**
     * Obtains {@link Role} object with data of specified Role from database by its name.
     * @param roleValue of specified {@link Role}
     * @return {@link Role} object
     * @throws DaoException if impossible to get by name
     */
    Role findByName(RoleValue roleValue);
}
