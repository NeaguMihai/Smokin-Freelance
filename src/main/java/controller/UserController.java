package controller;

import dao.UserModelDao;
import model.AppUserModel;
import model.UserModel;

import javax.swing.*;
import java.util.Optional;

public class UserController {

    private static final class SingletonHolder {
        public static final UserController INSTANCE = new UserController();
    }

    private UserModelDao userModelDAO;

    private UserController() {
        userModelDAO = new UserModelDao(
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


    public boolean loginRequest(String email, String password) {
        Optional<UserModel> user = userModelDAO.searchByMail("Users", email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                AppUserModel.getInstance().setId(user.get().getId());
                AppUserModel.getInstance().setEmail(user.get().getEmail());
                AppUserModel.getInstance().setName(user.get().getName());
                AppUserModel.getInstance().setJobs(user.get().getJobs());
                AppUserModel.getInstance().setLevel(user.get().getLevel());
                AppUserModel.getInstance().setMoney(user.get().getMoney());
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Wrong enail or password");

        return false;
    }

    public void updateMoney(int money) {
        userModelDAO.updateMoney(money);
    }


    public void deleteAccount(AppUserModel user)  {
        userModelDAO.deleteAccount(user.getId());
    }
}
