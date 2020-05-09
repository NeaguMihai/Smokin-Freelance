package dao;


import modelControllerInterfaces.ConnectionSwitched;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserModelDAO implements ConnectionSwitched {

    private Connection connection;

    private PreparedStatement registerRequestStatement;
    private PreparedStatement registerStatement;
    private PreparedStatement selectAllStatement;
    private PreparedStatement searchByMailTempStatement;
    private PreparedStatement searchByMailUsersStatement;
    private PreparedStatement modifyFriendListStatement;
    private PreparedStatement accountDeleteStatement;
    private PreparedStatement temporaryUserDeleteStatement;

    public UserModelDAO(Connection connection) {
        this.connection = connection;

        try {
            registerRequestStatement = connection.prepareStatement("INSERT INTO temp_users VALUES (?,?,?)");

            registerStatement = connection.prepareStatement("INSERT INTO Users VALUES (null,?,?,?,'', 0)");

            selectAllStatement = connection.prepareStatement("SELECT * FROM temp_users");

            searchByMailTempStatement = connection.prepareStatement("SELECT * FROM temp_users WHERE email = ?");

            searchByMailUsersStatement = connection.prepareStatement("SELECT * FROM Users WHERE email = ?");

            accountDeleteStatement = connection.prepareStatement("DELETE FROM Users WHERE id = ?");

            temporaryUserDeleteStatement = connection.prepareStatement("DELETE FROM temp_users WHERE email = ?");

            modifyFriendListStatement = connection.prepareStatement("UPDATE Users SET friends = ? WHERE email = ?");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean registerRequest(UserModel user) {
        try {
            registerRequestStatement.setString(1, user.getName());
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
            registerStatement.setString(1, user.getName());
            registerStatement.setString(2, user.getEmail());
            registerStatement.setString(3, user.getPassword());

            return registerStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<UserModel> selectAllUnregistered() {
        List<UserModel> users = new ArrayList<>();
        try {
            ResultSet rs = selectAllStatement.executeQuery();

            while (rs.next()) {
                UserModel usr = new UserModel(
                        0,
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        "",
                        0
                );
                users.add(usr);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public Optional<UserModel> searchByMail(String table, String mail) {


        try {
            ResultSet rs = null;

            if (table.equals("temp_users")) {
                searchByMailTempStatement.setString(1, mail);
                rs = searchByMailTempStatement.executeQuery();

                if (rs.next()) {
                    return Optional.of(
                            new UserModel(
                                        0,
                                    rs.getString("name"),
                                    rs.getString("email"),
                                    rs.getString("password"),
                                    "",
                                    0
                            )
                    );
                }
            }

            else if (table.equals("Users")) {
                searchByMailUsersStatement.setString(1, mail);
                rs = searchByMailUsersStatement.executeQuery();

                if (rs.next()) {
                    return Optional.of(
                            new UserModel(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("email"),
                                    rs.getString("password"),
                                    rs.getString("friends"),
                                    rs.getInt("buzz_number")
                            )
                    );
                }
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

    public boolean deleteTemporary(UserModel user) {

        try {
            temporaryUserDeleteStatement.setString(1, user.getEmail());

            return temporaryUserDeleteStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean updateFriendlist(String email, String friends) {
        try {
            modifyFriendListStatement.setString(1, friends);
            modifyFriendListStatement.setString(2, email);

            return modifyFriendListStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public void switchConnection(Connection connection) {
        this.connection = connection;
    }
}