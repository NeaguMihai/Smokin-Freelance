package model;

import AccountConfig.ReadWrite;

import java.util.*;

public class AppUserModel extends UserModel {

    private HistoryHolder historyHolder;

    private static final class SingletonHolder {
        private static final AppUserModel INSTANCE = new AppUserModel(
                0,
                "generic",
                "generic",
                "",
                0,
                0,
                ReadWrite.getInstance().getObject());
    }

    private AppUserModel(int id, String nume, String email, String jobs, int money, int level, HistoryHolder historyHolder) {
        super(id, nume, email, "c4ca4238a0b923820dcc509a6f75849b", jobs, money, level);
        this.historyHolder = historyHolder;
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
    public List<JobModel> getJobs() {
        return super.getJobs();
    }

    @Override
    public void setJobs(List<JobModel> friends) {
        super.setJobs(friends);
    }
}
