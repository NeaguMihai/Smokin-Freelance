package dao;

import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserModelDAO {

    private Connection connection;

    private PreparedStatement registerRequestStatement;
    private PreparedStatement registerStatement;
    private PreparedStatement selectAllStatement;
    private PreparedStatement searchByMailStatement;
    private PreparedStatement accountDeleteStatement;

    public UserModelDAO(Connection connection) {
        this.connection = connection;

        try {
            registerRequestStatement = connection.prepareStatement("INSERT INTO temp_users VALUES (?,?,?)");

            registerStatement = connection.prepareStatement("INSERT INTO Users VALUES (null,?,?,?)");

            selectAllStatement = connection.prepareStatement("SELECT * FROM temp_users");

            searchByMailStatement = connection.prepareStatement("SELECT * FROM ? WHERE email = ?");

            accountDeleteStatement = connection.prepareStatement("DELETE * FROM Users WHERE id = ?");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean registerRequest(UserModel user) {
        try {
            registerRequestStatement.setString(1, user.getNume());
            registerRequestStatement.setString(2, user.getEmail());
            registerRequestStatement.setString(3, user.getPassword());

            return registerRequestStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean register(UserModel user) {
        try {
            registerStatement.setString(1, user.getNume());
            registerStatement.setString(2, user.getEmail());
            registerStatement.setString(3, user.getPassword());

            return registerStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<UserModel> selectAll() {
        List<UserModel> users = new ArrayList<>();
        try {
            ResultSet rs = selectAllStatement.executeQuery();

            while (rs.next()) {
                UserModel usr = new UserModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        "Password Holder"
                );


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public Optional<UserModel> searchByMail(String table, String mail) {

        try {

            searchByMailStatement.setString(1, table);
            searchByMailStatement.setString(2, mail);

            ResultSet rs = searchByMailStatement.executeQuery();

            if (rs.next()) {
                return Optional.of(
                        new UserModel(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public boolean deleteAccount(int id) {

        try {
            accountDeleteStatement.setInt(1, id);

            return accountDeleteStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

}