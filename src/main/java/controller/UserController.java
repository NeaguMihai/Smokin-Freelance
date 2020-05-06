package controller;

import dao.UserModelDAO;
import model.AppUserModel;
import model.UserModel;

import javax.swing.*;
import java.util.Optional;

public class UserController {

    private static final class SingletonHolder {
        public static final UserController INSTANCE = new UserController();
    }

    private UserModelDAO userModelDAO;

    private UserController() {
        userModelDAO = new UserModelDAO(
                ConnectionManager.getInstance().getConnection()
        );
    }

    public static UserController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean registerRequest( UserModel user) {

        if (user.getName().equals("")||user.getEmail().equals("")||user.getPassword().equals("")) {
            JOptionPane.showMessageDialog(null, "All fields must be completed");
            return false;
        } else{

            Optional<UserModel> exists_temp = userModelDAO.searchByMail("temp_users", user.getEmail());
            Optional<UserModel> exists_user = userModelDAO.searchByMail("Users", user.getEmail());

            if (exists_temp.isEmpty() && exists_user.isEmpty())
                return userModelDAO.registerRequest(user);
            else
                JOptionPane.showMessageDialog(null,"we couldn't complete this operation");
                return false;
        }

    }

    public boolean register(UserModel user) {

        Optional<UserModel> exists = userModelDAO.searchByMail("Users", user.getEmail());

        if (exists.isEmpty())
            return userModelDAO.register(user);
        else
            return false;
    }



    public boolean loginRequest(String email, String password) {
        Optional<UserModel> user = userModelDAO.searchByMail("Users", email);
        System.out.println(user.get().getEmail());
        System.out.println(user.get().getPassword());
        if (!user.isEmpty()) {
            if (user.get().getPassword().equals(password)) {
                AppUserModel.getInstance().setId(user.get().getId());
                AppUserModel.getInstance().setEmail(user.get().getEmail());
                AppUserModel.getInstance().setName(user.get().getName());
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Wrong enail or password");

        return false;
    }
}
