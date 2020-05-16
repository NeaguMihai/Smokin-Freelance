package model;

import java.util.*;

public class AppUserModel extends UserModel {


    private static final class SingletonHolder {
        private static final AppUserModel INSTANCE = new AppUserModel(
                0,
                "generic",
                "generic",
                "",
                0,
                0
        );
    }

    private AppUserModel(int id, String nume, String email, String jobs, int money, int level) {
        super(id, nume, email, "c4ca4238a0b923820dcc509a6f75849b", jobs, money, level);
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
    public List<JobModel> getJobList() {
        return super.getJobList();
    }

    @Override
    public void setJobList(List<JobModel> friends) {
        super.setJobList(friends);
    }

    @Override
    public String getJobs() {
        return super.getJobs();
    }

    @Override
    public void setJobs(String jobs) {
        super.setJobs(jobs);
    }
}
