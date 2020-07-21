package dao;


import model.AppUserModel;
import modelControllerInterfaces.ConnectionSwitched;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserModelDao implements ConnectionSwitched {

    private Connection connection;

    private PreparedStatement registerRequestStatement;
    private PreparedStatement registerStatement;
    private PreparedStatement selectAllStatement;
    private PreparedStatement searchByMailTempStatement;
    private PreparedStatement updateMoneyStatement;
    private PreparedStatement updateLevelStatement;
    private PreparedStatement updateJobsStatement;
    private PreparedStatement searchByMailUsersStatement;
    private PreparedStatement searchByIdUsersStatement;
    private PreparedStatement accountDeleteStatement;
    private PreparedStatement temporaryUserDeleteStatement;

    public UserModelDao(Connection connection) {
        this.connection = connection;

        try {
            registerRequestStatement = connection.prepareStatement("INSERT INTO temp_users VALUES (?,?,?)");
            registerStatement = connection.prepareStatement("INSERT INTO Users VALUES (null,?,?,?,'', 10000, 5)");
            updateMoneyStatement = connection.prepareStatement("UPDATE Users SET money = ? WHERE id = ?");
            updateLevelStatement = connection.prepareStatement("UPDATE Users SET level = ? WHERE id = ?");
            updateJobsStatement = connection.prepareStatement("UPDATE Users SET jobs = ? WHERE id = ?");
            selectAllStatement = connection.prepareStatement("SELECT * FROM temp_users");
            searchByMailTempStatement = connection.prepareStatement("SELECT * FROM temp_users WHERE email = ?");
            searchByMailUsersStatement = connection.prepareStatement("SELECT * FROM Users WHERE email = ?");
            searchByIdUsersStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
            accountDeleteStatement = connection.prepareStatement("DELETE FROM Users WHERE id = ?");
            temporaryUserDeleteStatement = connection.prepareStatement("DELETE FROM temp_users WHERE email = ?");

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

    //metoda cae inregistreaza un user un uset_temp
    public void register(UserModel user) {
        try {
            registerStatement.setString(1, user.getName());
            registerStatement.setString(2, user.getEmail());
            registerStatement.setString(3, user.getPassword());

            registerStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //metoda care returneaza o lista cu toti userii din temp_users
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
                        0,
                        0
                );
                users.add(usr);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    //metoda carereturneaza un optional cu o instanta de UserModel in caz ca userul cu emailul respectiv a fost gasit
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
                                    0,
                                    0
                            )
                    );
                }
            }

            else if (table.equals("Users")) {
                searchByMailUsersStatement.setString(1, mail);
                rs = searchByMailUsersStatement.executeQuery();

                if (rs.next()) {
                    Optional<UserModel> opt =  Optional.of(
                            new UserModel(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("email"),
                                    rs.getString("password"),
                                    rs.getString("jobs"),
                                    rs.getInt("money"),
                                    rs.getInt("level")

                            )
                    );
                    return opt;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    //metoda care sterge contul
    public boolean deleteAccount(int id) {

        try {
            accountDeleteStatement.setInt(1, id);

            return accountDeleteStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    //metoda care modifica nivelul unui user
    public void updateLevel(int level, int id) {
        try {
            updateLevelStatement.setInt(1,level);
            updateLevelStatement.setInt(2,id);
            updateLevelStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //metoda care sterge un user din tabelul de useri temporari
    public void deleteTemporary(UserModel user) {

        try {
            temporaryUserDeleteStatement.setString(1, user.getEmail());

            temporaryUserDeleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void updateMoney(int money, int id) {
        try {
            searchByIdUsersStatement.setInt(1, id);

            ResultSet rs = searchByIdUsersStatement.executeQuery();
            int sum = 0;
            while (rs.next()) {
                 sum = rs.getInt("money");
            }

            updateMoneyStatement.setInt(1,sum + money);
            updateMoneyStatement.setInt(2, id);

            updateMoneyStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateMoney() {
        try {
            searchByIdUsersStatement.setInt(1, AppUserModel.getInstance().getId());

            updateMoneyStatement.setInt(1,AppUserModel.getInstance().getMoney());
            updateMoneyStatement.setInt(2, AppUserModel.getInstance().getId());

            return updateMoneyStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Integer> updateInfo() {
        try {
            searchByIdUsersStatement.setInt(1, AppUserModel.getInstance().getId());
            ResultSet rs = searchByIdUsersStatement.executeQuery();
            List<Integer> list = new LinkedList<>();
            while (rs.next()) {
                list.add(rs.getInt("money"));
                list.add(rs.getInt("level"));

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();
    }

    public boolean updateJobs(String string) {
        try {

            updateJobsStatement.setString(1,string);
            updateJobsStatement.setInt(2, AppUserModel.getInstance().getId());

            return updateJobsStatement.executeUpdate() != 1;
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