package controller;

import dao.JobModelDao;
import model.AppUserModel;
import model.JobModel;

import javax.swing.*;
import java.util.LinkedList;

public class JobController {

    private JobModelDao jobModelDao;

    private JobController(JobModelDao jobModelDao) {
        this.jobModelDao = jobModelDao;
    }

    private static final class SingletonHolder {
        private static final JobController INSTANCE = new JobController(new JobModelDao(ConnectionManager.getInstance().getConnection()));
    }

    public boolean addJob(String name, int money, int difficulty, String details) {
        return jobModelDao.addJob(name, money, AppUserModel.getInstance().getId(), details, difficulty);
    }

    public void deleteJob(int id) {
        jobModelDao.deleteJob(id);
    }

    public void occupyJob(int id) {
        jobModelDao.changeAvailability(0,id);
    }

    public void freeJob(int id) {
        jobModelDao.changeAvailability(1,id);
    }

    public LinkedList<JobModel> getJobList() {
        return jobModelDao.getJobs();
    }

    public LinkedList<JMenu> getPostedJobs(int id) {
        return jobModelDao.getPostedJobs(id);
    }

    public void getAcceptedJobs() {

        jobModelDao.getAcceptedJobs();
    }


    public static JobController getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
