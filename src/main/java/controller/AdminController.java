package controller;


import dao.UserModelDAO;

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
                userModelDAO.changeConnection(ConnectionManager.getInstance().getConnection());
                return true;
            }
        }
        return false;
    }

    public boolean normalConnection() {
        boolean action = ConnectionManager.getInstance().defaultConnection();
        userModelDAO.changeConnection(ConnectionManager.getInstance().getConnection());
        return action;
    }


}
