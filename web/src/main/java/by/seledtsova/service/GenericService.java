package by.seledtsova.service;

import java.util.List;

/**
 * Provides the methods to interact with representing entity data.
 * @param <I> value of a primitive types of entity fields
 * @param <T> type of entity
 */
public interface GenericService<I, T> {

    /**
     * Obtains {@link T} objects collection from data source.
     * @throws SecurityException if impossible to get {@link T}
     * @return collection of {@link T} objects
     */
    List<T> getAll();

    /**
     * Obtains {@link T} object with data of specified entity from database by its ID.
     * @param id ID of specified {@link T}
     * @throws SecurityException if not in the database specified {@link T}
     * @return {@link T} object
     */
    T findById(I id);

    /**
     * Adds new {@link T} to entity table.
     * @param entity  {@link T} object with new data and ID to put data to
     * @throws SecurityException if impossible to save new {@link T}
     */
    void add(T entity);

    /**
     * Removes specified {@link T} entry.
     * @param id ID of entry to look for and delete {@link T}
     * @throws SecurityException if not in the database specified {@link T}
     */
    void removeById(I id);

    /**
     * Updates specified {@link T} entry.
     * @param entity {@link T} object that is being updated
     * @throws SecurityException if not in the database specified {@link T}
     */
    void update(T entity);
}
