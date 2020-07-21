package controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    //userul basic trebuie creat un user separat in baza de date cu privilegii minime
    private String user = "app_user";
    private String password = "parolauser";
    private Connection connection;

    private static final class SingletonHolder {
        private static final ConnectionManager INSTANCE = new ConnectionManager();
    }

    private ConnectionManager() {
        //COnectarea la baza de date
        String url = "jdbc:mysql://smokin.c1wm9jygabmd.eu-central-1.rds.amazonaws.com/smokin";

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
        String url = "jdbc:mysql://smokin.c1wm9jygabmd.eu-central-1.rds.amazonaws.com/smokin";

        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"There was an error when establishing the connection");
            throw new RuntimeException();

        }


        return true;
    }

    public void defaultConnection() {
        String url = "jdbc:mysql://smokin.c1wm9jygabmd.eu-central-1.rds.amazonaws.com/smokin";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"There was an error when establishing the connection");
        }
    }

}
