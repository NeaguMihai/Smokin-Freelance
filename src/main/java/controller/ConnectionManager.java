package controller;

import modelControllerInterfaces.ConnectionSwitched;
import modelControllerInterfaces.DaoConnectionUpdate;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ConnectionManager {

    private String user = "user";
    private String password = "uLMUJ5dU4Myf1J6N";
    private Connection connection;

    private static final class SingletonHolder {
        private static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    private ConnectionManager() {
        String url = "jdbc:mysql://localhost/Smokin_Freelance?serverTimezone=UTC";

        try {
            connection = DriverManager.getConnection(url, user,password);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public  static ConnectionManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean adminConnection(String name, String password) {
        String url = "jdbc:mysql://localhost/Smokin_Freelance?serverTimezone=UTC";

        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"There was an error when establishing the connection");
            throw new RuntimeException();

        }


        return true;
    }

    public boolean defaultConnection() {
        String url = "jdbc:mysql://localhost/Smokin_Freelance?serverTimezone=UTC";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"There was an error when establishing the connection");
            return false;
        }
       return true;
    }

}
