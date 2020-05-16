package model;

public class JobModel {
    private int id;
    private String name;
    private int money;
    private int level;
    private int status;
    private String details;


    public JobModel(int id, String name, int money,int level , String details, int status) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.details = details;
        this.level = level;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public String getDetails() {
        return details;
    }
    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                  name + money;
    }
}
