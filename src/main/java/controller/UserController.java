package controller;

import AccountConfig.ReadWrite;
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


    public boolean loginRequest(String email, String password) {
        Optional<UserModel> user = userModelDAO.searchByMail("Users", email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                AppUserModel.getInstance().setId(user.get().getId());
                AppUserModel.getInstance().setEmail(user.get().getEmail());
                AppUserModel.getInstance().setName(user.get().getName());
                AppUserModel.getInstance().setFriends(user.get().getFriends());
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Wrong enail or password");

        return false;
    }

    public boolean searchFriend(String mail) {
        Optional<UserModel> user = userModelDAO.searchByMail("Users", mail);
        if (!user.isEmpty()) {
            AppUserModel.getInstance().addFriend(user.get());
            updateFriendList(AppUserModel.getInstance().getEmail(),AppUserModel.getInstance().getFriendsString());

            return true;
        }else {
            JOptionPane.showMessageDialog(null, "No user found");
        }
        return false;

    }

    public boolean updateFriendList(String email, String string) {

        return userModelDAO.updateFriendlist(email,string);
    }

    public void deleteAccount(AppUserModel user)  {
        userModelDAO.deleteAccount(user.getId());
    }
}
