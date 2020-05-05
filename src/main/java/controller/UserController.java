package controller;

import dao.UserModelDAO;
import model.AppUserModel;
import model.UserModel;
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

        Optional<UserModel> exists_temp = userModelDAO.searchByMail("temp_users", user.getEmail());
        Optional<UserModel> exists_user = userModelDAO.searchByMail("Users", user.getEmail());

        if (exists_temp.isEmpty() && exists_user.isEmpty())
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
        Optional<UserModel> user = userModelDAO.searchByMail("Users", email);

        if (!user.isEmpty()) {
            if (user.get().getPassword().equals(password)) {
                AppUserModel.getInstance().setId(user.get().getId());
                AppUserModel.getInstance().setEmail(user.get().getEmail());
                AppUserModel.getInstance().setName(user.get().getName());
                return true;
            }
        }

        return false;
    }
}
