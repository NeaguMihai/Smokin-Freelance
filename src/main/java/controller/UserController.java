package controller;

import dao.UserModelDAO;
import model.UserModel;

import java.sql.Connection;
import java.util.List;
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

        Optional<UserModel> exists = userModelDAO.searchByMail("temp_users", user.getEmail());

        if (exists.isEmpty())
            return userModelDAO.registerRequest(user);
        else
            return false;
    }

    public boolean register(UserModel user) {

        Optional<UserModel> exists = userModelDAO.searchByMail("Users", user.getEmail());

        if (exists.isEmpty())
            return userModelDAO.register(user);
        else
            return false;
    }



    public boolean loginRequest(String email, String password) {

    }
}
