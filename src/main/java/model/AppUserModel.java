package model;

public class AppUserModel extends UserModel {

    private static final class SingletonHolder {
        private static final AppUserModel INSTANCE = new AppUserModel(0,"generic","generic");
    }

    public AppUserModel(int id, String nume, String email) {
        super(id, nume, email, "");
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    public static AppUserModel getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
