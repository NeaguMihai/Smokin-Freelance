package dao;

import model.AppUserModel;
import model.JobModel;

import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class JobModelDao {

    private Connection connection;

    private PreparedStatement addJobStatement;
    private PreparedStatement deleteJobStatement;
    private PreparedStatement searchAllJobsStatement;
    private PreparedStatement searchByIdStatement;
    private PreparedStatement changeAvailabilityStatement;
    private PreparedStatement setCompleteStatement;
    private PreparedStatement selectJobsByIdsStatement;

    public JobModelDao(Connection connection) {
        this.connection = connection;

        try {
            addJobStatement = connection.prepareStatement("INSERT INTO jobs VALUES (null,?,1,?,?,?,?,0)");
            deleteJobStatement = connection.prepareStatement("DELETE FROM jobs WHERE id = ?");
            searchAllJobsStatement = connection.prepareStatement("SELECT * FROM jobs WHERE available = 1");
            searchByIdStatement = connection.prepareStatement("SELECT * FROM jobs WHERE poster_id = ?");
            changeAvailabilityStatement = connection.prepareStatement("UPDATE jobs SET available = ? WHERE id = ?");
            setCompleteStatement = connection.prepareStatement("UPDATE jobs SET finisher_id = ? WHERE id = ?");
            selectJobsByIdsStatement = connection.prepareStatement("SELECT * FROM jobs WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //metoda care adauga un job in baza de date
    public boolean addJob(String name, int money, int posterId, String details, int difficulty) {
        try {
            addJobStatement.setString(1,name);
            addJobStatement.setInt(2,money);
            addJobStatement.setInt(3,difficulty);
            addJobStatement.setString(4,details);
            addJobStatement.setInt(5,posterId);

            return addJobStatement.executeUpdate() != 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //metoda care cauta cui trebuie sa i se plateasca banii pentru job
    public List<Integer> getFinisher(int id) {
        try {
            selectJobsByIdsStatement.setInt(1, id);
            ResultSet rs = selectJobsByIdsStatement.executeQuery();

            List<Integer> list = new LinkedList<>();

            while(rs.next()) {

                list.add(rs.getInt("finisher_id"));
                list.add(rs.getInt("money"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new LinkedList<>();

    }

    //metoda care sterge un job din baza de date
    public boolean deleteJob(int id) {

        try {
            deleteJobStatement.setInt(1, id);
            deleteJobStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //metoda care returneaza o lista cu toate joburile
    public LinkedList<JobModel> getJobs() {
        LinkedList<JobModel> jm = new LinkedList<>();
        try {
            ResultSet rs = searchAllJobsStatement.executeQuery();

            while (rs.next()) {
                if (rs.getInt("poster_id") != AppUserModel.getInstance().getId())
                jm.add(new JobModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("money"),
                        rs.getInt("difficulty"),
                        rs.getString("details"),
                        rs.getInt("available")
                ));

            }
            return  jm;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    //metoda care schimba disponibilitatea unui job
    public boolean changeAvailability(int status, int id) {
        try {
            changeAvailabilityStatement.setInt(1, status);
            changeAvailabilityStatement.setInt(2, id);
            return changeAvailabilityStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    //metoda care seteaza persoana care a terminat jobul
    public boolean setFinisher(int id) {
        try {
            setCompleteStatement.setInt(1, AppUserModel.getInstance().getId());
            setCompleteStatement.setInt(2, id);
            return setCompleteStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //metoda ce returneaza joburile postate de o persoana
    public LinkedList<JMenu> getPostedJobs(int id) {
        LinkedList<JMenu> jm = new LinkedList<>();
        try {
            searchByIdStatement.setInt(1, id);

            ResultSet rs = searchByIdStatement.executeQuery();

            while (rs.next()) {
                jm.add(new JMenu(rs.getString("name")
                        + "!@#!@#!@#"
                        + rs.getInt("available")
                        + "!@#!@#!@#"
                        + rs.getInt("id")));
            }
            return jm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jm;
    }

    //metoda ce seteaza in AppUserModel
    public void getAcceptedJobs() {

            if (!AppUserModel.getInstance().getJobs().equals("")) {

            String [] str = AppUserModel.getInstance().getJobs().split(",");

            List<Integer> list = Arrays.stream(str).map(Integer::parseInt).collect(Collectors.toList());
            AppUserModel.getInstance().getJobList().clear();
            list.forEach(e -> {
                try {
                    selectJobsByIdsStatement.setInt(1, e);
                    ResultSet rs = selectJobsByIdsStatement.executeQuery();

                    while (rs.next()) {
                        JobModel jm = new JobModel(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("money"),
                                rs.getInt("difficulty"),
                                rs.getString("details"),
                                rs.getInt("available")
                        );


                        AppUserModel.getInstance().getJobList().add(jm);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            });


            }else
                AppUserModel.getInstance().getJobList().clear();

    }

}
