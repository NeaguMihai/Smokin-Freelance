package controller;


import dao.JobModelDao;
import dao.UserModelDao;
import guiComponents.AdminContacts;
import model.UserModel;

import java.util.List;

public class AdminController {


    private static final class SingletonHolder {
        public static final AdminController INSTANCE = new AdminController();
    }

    private UserModelDao userModelDAO;
    private JobModelDao jobModelDao;


    private AdminController() {
        userModelDAO = new UserModelDao(
                ConnectionManager.getInstance().getConnection()
        );
        jobModelDao = new JobModelDao(
                ConnectionManager.getInstance().getConnection()
        );
    }

    public static AdminController getInstance() {

        return SingletonHolder.INSTANCE;
    }
    //metoda pentru login ca admin
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
    //metoda care schimba conexiunea din user in admin
    public void normalConnection() {

        ConnectionManager.getInstance().defaultConnection();
        userModelDAO.switchConnection(ConnectionManager.getInstance().getConnection());
    }
    //metoda folosita in admin frame pentru a sterge o cerere de inregistrare
    public void deleteRequest(AdminContacts user) {
        AdminContacts adm = new AdminContacts(user.getUser(),user.getController());
        userModelDAO.deleteTemporary(user.getUser());
        adm.updateFrame(adm.getController());

    }
    //metoda care inregistreaza un user din panelul de admin
    public void addToUsers(AdminContacts user) {
        userModelDAO.register(user.getUser());
        deleteRequest(user);
    }


    public List<UserModel> getUnregisters() {
        return userModelDAO.selectAllUnregistered();
    }




}
