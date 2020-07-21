package controller;

import dao.JobModelDao;
import model.AppUserModel;
import model.JobModel;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

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
    //
    public void deleteJob(int id) {
        jobModelDao.deleteJob(id);
    }

    public void occupyJob(int id) {
        jobModelDao.changeAvailability(0,id);
    }

    public void freeJob(int id) {
        jobModelDao.changeAvailability(1,id);
    }
    //schimba disponibilitatea job.ului in 2( ce se comporta ca un flag ca jobul este complet)
    public void finishJob(int id) {
        jobModelDao.changeAvailability(2, id);
    }
    //metoda care seteaza id.ul persioanei care a terminat jobul
    public void finishAndPay(int id) {
        jobModelDao.setFinisher(id);
    }

    //metoda care trimite banii  catre userul care a completat jobul
    public void payForJob(int id) {

        List<Integer> list = jobModelDao.getFinisher(id);


        UserController.getInstance().updateMoney(list.get(1), list.get(0));
    }
    //metoda care returneaza lista cu model de joburi
    public LinkedList<JobModel> getJobList() {
        return jobModelDao.getJobs();
    }

    //metoda care returneaza lista cu joburi postate de user
    public LinkedList<JMenu> getPostedJobs(int id) {
        return jobModelDao.getPostedJobs(id);
    }

    //metoda care seteaza lista cu joburile acceptate in AppUserModel
    public void getAcceptedJobs() {

        jobModelDao.getAcceptedJobs();
    }


    public static JobController getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
