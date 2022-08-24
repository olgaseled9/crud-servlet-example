package by.seledtsova.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import by.seledtsova.exception.DaoException;

/**
 * Creates connection to a data source.
 */
public class ConnectionToDatabase {

    /**
     * database's driver
     */
    private static final String DRIVER = "org.h2.Driver";
    /**
     * database's URL
     */
    private static final String URL = "jdbc:h2:~/test";
    /**
     * database's username
     */
    private static final String USER = "SA";
    /**
     * database's password
     */
    private static final String PASSWORD = "";

    static {
        loadDriver();
    }

    /**
     * This method used to establish connection to data source.
     * @return established connection
     * @throws DaoException  if unable to establish connection
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e) {
            throw new DaoException("Connection is not available.", e);
        }
    }

    /**
     * This method used to establish connection to data storage.
     */
    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        }
        catch (ClassNotFoundException e) {
            throw new DaoException("ERROR: failed to load JDBC driver.", e);
        }
    }

}

