package dao;

import controller.AdminController;
import controller.ConnectionManager;
import model.JobModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Optional;

public class JobModelDao {

    private Connection connection;

    private PreparedStatement addJobStatement;
    private PreparedStatement deleteJobStatement;
    private PreparedStatement searchAllJobsStatement;
    private PreparedStatement searchByIdStatement;
    private PreparedStatement changeAvailabilityStatement;
    private PreparedStatement addLogStatement;
    private PreparedStatement logRequestStatement;

    public JobModelDao(Connection connection) {
        this.connection = connection;

        try {
            addJobStatement = connection.prepareStatement("INSERT INTO jobs VALUES (null,?,1,?,?,?,?)");
            deleteJobStatement = connection.prepareStatement("DELETE FROM jobs WHERE id = ?");
            searchAllJobsStatement = connection.prepareStatement("SELECT * FROM jobs WHERE available = 1");
            searchByIdStatement = connection.prepareStatement("SELECT * FROM jobs WHERE poster_id = ?");
            changeAvailabilityStatement = connection.prepareStatement("UPDATE jobs SET available = ? WHERE id = ?");
            addLogStatement = connection.prepareStatement("INSERT INTO logs VALUES (null, ?)");
            logRequestStatement = connection.prepareStatement("SELECT * FROM logs ORDER BY id DESC");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public LinkedList<JobModel> getJobs() {
        LinkedList<JobModel> jm = new LinkedList<>();
        try {
            ResultSet rs = searchAllJobsStatement.executeQuery();

            while (rs.next()) {
                jm.add(new JobModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("money"),
                        rs.getInt("difficulty"),
                        rs.getString("details")
                ));
            }
            return  jm;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

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

    public boolean addLog(String log) {
        try {
            addLogStatement.setString(1, log);

            return addLogStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public LinkedList<String> logRequest() {
        LinkedList<String> list = new LinkedList<>();
        try {
            ResultSet rs = logRequestStatement.executeQuery();

            while (rs.next()) {
                    list.add(rs.getInt("id") + " " + rs.getString("event"));

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    public LinkedList<JMenu> getPostedJobs(int id) {
        LinkedList<JMenu> jm = new LinkedList<>();
        try {
            searchByIdStatement.setInt(1, id);

            ResultSet rs = searchByIdStatement.executeQuery();

            while (rs.next()) {
                jm.add(new JMenu(rs.getString("name")));
            }
            return jm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jm;
    }




}
