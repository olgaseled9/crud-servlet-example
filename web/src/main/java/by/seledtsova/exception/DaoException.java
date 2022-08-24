package by.seledtsova.exception;
/**
 * Thrown if it is not possible to do crud operations.
 */
public class DaoException extends RuntimeException {


    public DaoException(String message) {
        super(message);
    }
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
