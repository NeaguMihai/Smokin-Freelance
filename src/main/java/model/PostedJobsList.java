package model;

import controller.JobController;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PostedJobsList extends JobListModel{

    private PostedJobsList() {

    }

    private static final class SingletonHolder {
        private static final PostedJobsList INSTANCE = new PostedJobsList();
    }

    public void createJobList(JPopupMenu joblist) {

        super.createJobList(joblist, JobController.getInstance().getPostedJobs(AppUserModel.getInstance().getId()));
    }

    @Override
    public void JobListAction(JMenu menu) {
        LinkedList<JMenuItem> jobAction = new LinkedList<>();
        jobAction.add(new JMenuItem("Mark as finished"));
        jobAction.add(new JMenuItem("Delete job"));

        jobAction.forEach(e -> {
            e.setBackground(Color.RED.darker());
            e.setForeground(Color.WHITE);
            e.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        });

        String [] token = menu.getText().split("!@#!@#!@#");
        if (Integer.parseInt(token[1]) != 2)
            jobAction.get(0).setEnabled(false);
        menu.setText(token[0]);
        int id = Integer.parseInt(token[2]);

        jobAction.forEach(menu::add);

        addButtonActions(jobAction, menu, id);
    }


    protected void addButtonActions(List<JMenuItem> list, JMenu menu, int id) {
        list.get(0).addActionListener(e -> {
            JobController.getInstance().payForJob(id);
            deleteJob(id);
        });

        list.get(1).addActionListener(e -> {
            deleteJob(id);
            JOptionPane.showMessageDialog(null,"Deleted successfully");

        });
    }

    private void deleteJob(int id) {
        JobController.getInstance().deleteJob(id);
        String newJobList = Arrays.stream(
                AppUserModel.getInstance().getJobs().split(","))
                .filter(ev -> !ev.equals(String.valueOf(id)))
                .collect(Collectors.joining(","));
        AppUserModel.getInstance().setJobs(newJobList);
    }

    public static PostedJobsList getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
