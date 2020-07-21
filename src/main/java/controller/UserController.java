package controller;

import dao.UserModelDao;
import model.AppUserModel;
import model.UserModel;

import javax.swing.*;
import java.util.List;
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

    //metoda care verifica daca un user exista deja in baza de date
    //daca nu, il inregistreaza
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


    //metoda care seteaza AppUserModel cu datele userului din baza de date
    //daca parola si mailul sunt corecte
    public boolean loginRequest(String email, String password) {
        Optional<UserModel> user = userModelDAO.searchByMail("Users", email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                AppUserModel.getInstance().setId(user.get().getId());
                AppUserModel.getInstance().setEmail(user.get().getEmail());
                AppUserModel.getInstance().setName(user.get().getName());
                AppUserModel.getInstance().setJobs(user.get().getJobs());
                AppUserModel.getInstance().setJobList(user.get().getJobList());
                AppUserModel.getInstance().setLevel(user.get().getLevel());
                AppUserModel.getInstance().setMoney(user.get().getMoney());

                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Wrong enail or password");

        return false;
    }
    //metoda care updateaza banii dupa postarea unui job
    public void updateMoney(int money) {
        userModelDAO.updateMoney(money, AppUserModel.getInstance().getId());
    }

    //metoda care updateaza banii
    public void updateMoney() {
        userModelDAO.updateMoney();
    }

    public void updateMoney(int money, int id) {
        userModelDAO.updateMoney(money, id);
    }

    //metoda care updateaza lista de joburi din baza de date
    public void updateJobs(String string) {
        userModelDAO.updateJobs(string);
    }

    //metoda care scade nivelul userului din baza de date
    public void decreaseLevel() {
        int newLevel = AppUserModel.getInstance().getLevel() -1;
        if (newLevel < 3)
            userModelDAO.deleteAccount(AppUserModel.getInstance().getId());
        else
            userModelDAO.updateLevel(newLevel, AppUserModel.getInstance().getId());
    }

    //metoda care creste nivelul userului din baza de date
    public void increaseLevel() {
        int newLevel = AppUserModel.getInstance().getLevel() + 1;
        if (newLevel > 10)
            newLevel = 10;
        userModelDAO.updateLevel(newLevel, AppUserModel.getInstance().getId());
    }

    //metoda care updateaza nivelul si banii userului local cu datele din baza de date
    public List<Integer> updateInfo() {
        return userModelDAO.updateInfo();
    }

    public void deleteAccount(AppUserModel user)  {
        userModelDAO.deleteAccount(user.getId());
    }
}
