package controller;


import dao.UserModelDAO;
import guiComponents.AdminContacts;
import model.UserModel;

import java.util.List;

public class AdminController {


    private static final class SingletonHolder {
        public static final AdminController INSTANCE = new AdminController();
    }

    private UserModelDAO userModelDAO;

    private AdminController() {
        userModelDAO = new UserModelDAO(
                ConnectionManager.getInstance().getConnection()
        );
    }

    public static AdminController getInstance() {

        return SingletonHolder.INSTANCE;
    }

    public boolean adminLogin(String name, String password) {


        if (name.equals(""))
            return false;
        else {
            if (ConnectionManager.getInstance().adminConnection(name, password)) {
                userModelDAO.switchConnection(ConnectionManager.getInstance().getConnection());
                return true;
            }
        }
        return false;
    }

    public boolean normalConnection() {
        boolean action = ConnectionManager.getInstance().defaultConnection();
        userModelDAO.switchConnection(ConnectionManager.getInstance().getConnection());
        return action;
    }

    public void deleteRequest(AdminContacts user) {
        AdminContacts adm = new AdminContacts(user.getUser(),user.getController());
        userModelDAO.deleteTemporary(user.getUser());
        adm.updateFrame(adm.getController());

    }

    public void addToUsers(AdminContacts user) {
        userModelDAO.register(user.getUser());
        deleteRequest(user);
    }

    public List<UserModel> getUnregisters() {
        return userModelDAO.selectAllUnregistered();
    }




}
