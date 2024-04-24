package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    private static Database instance = null;
    private Connection connection = null;

    // url for database
    private final String DB_URL = "jdbc:postgresql://localhost:5432/tourismagency";

    //db user name
    private final String DB_USER = "postgres";

    //db user pass
    private final String DB_PASS = "Postgre";

    // method for establishing database connection
    private Database () {
        try {
            // to initialize the first value of connection, url, user and password are being hold.
            this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // returning the connection
    public Connection getConnection() {
        return connection;
    }

    // instance of connection process
    public static Connection getInstance(){
        try {
            // checks if connection is null or closed
            if (instance == null || instance.getConnection().isClosed()) {
                // calls Database function
                instance = new Database();
            }
        } catch (SQLException e) {
            // throws and exception
e.printStackTrace();        }

        // and returning the connection
        return instance.getConnection();

    }
}

