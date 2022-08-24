package by.seledtsova.converter;

/**
 * Converts a container of entity data object into a container of represented data object and back.
 * @param <I> represented data object
 * @param <O> display data object
 */
public interface GenericConverter<I, O> {

    /**
     * Converts from entity data object to represented data object.
     * @param entity  entity data
     * @return page's represented data object
     */
    I convertToDto(O entity);

    /**
     * Converts from display data object to represented data object.
     * @param entity represented data
     * @return entity data object
     */
    O convertBack(I entity);
}

