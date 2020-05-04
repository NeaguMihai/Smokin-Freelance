package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private String user = "user";
    private String password = "uLMUJ5dU4Myf1J6N";
    private Connection connection;
    private String database;

    private static final class SingletonHolder {
        public static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    private ConnectionManager() {
        String url = "jdbc:mysql://localhost/Smokin_Chat?serverTimezone=UTC";

        try {
            connection = DriverManager.getConnection(url, user,password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public  static ConnectionManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

}
