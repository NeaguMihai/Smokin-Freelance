package model;

import java.util.*;

public class AppUserModel extends UserModel {


    private static final class SingletonHolder {
        private static final AppUserModel INSTANCE = new AppUserModel(0, "generic", "generic","", 0);
    }

    public AppUserModel(int id, String nume, String email, String friends, int buzzNumber) {
        super(id, nume, email, "1", friends,buzzNumber);
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

    @Override
    public List<FriendModel> getFriends() {
        return super.getFriends();
    }

    @Override
    public void setFriends(String friends) {
        super.setFriends(friends);
    }

    @Override
    public void setFriends(List<FriendModel> friends) {
        super.setFriends(friends);
    }
}
