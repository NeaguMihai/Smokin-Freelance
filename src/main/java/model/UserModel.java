package model;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserModel {

    private int id;

    private String name;

    private String email;

    private String password;

    private int money;

    private String jobs;

    private int level;

    private List<JobModel> joblist;

    public UserModel(int id, String name, String email, String password, String jobs, int money, int level) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.money = money;
        this.level = level;
        this.jobs = jobs;
        this.joblist = new LinkedList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", nume='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public void setJobList(List<JobModel> jobs) {
        this.joblist = jobs;
    }



    public List<JobModel> getJobList() {
        return joblist;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }


}
