package model;

import controller.JobController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class PostedJobsList extends JobListModel{

    private PostedJobsList() {

    }

    private static final class SingletonHolder {
        private static final PostedJobsList INSTANCE = new PostedJobsList();
    }

    public void createJobList(JPopupMenu joblist) {
        LinkedList<JMenuItem> jobAction = new LinkedList<>();
        jobAction.add(new JMenuItem("Delete job"));

        super.createJobList(joblist, jobAction, JobController.getInstance().getPostedJobs(AppUserModel.getInstance().getId()));
    }

    @Override
    public void friendListAction(JMenu menu) {
        LinkedList<JMenuItem> jobAction = new LinkedList<>();
        jobAction.add(new JMenuItem("Delete job"));

        jobAction.forEach(e -> {
            e.setBackground(Color.RED.darker());
            e.setForeground(Color.WHITE);
            e.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        });

        jobAction.forEach(menu::add);

        addButtonActions(jobAction, menu);
    }

    @Override
    protected void addButtonActions(List<JMenuItem> list, JMenu menu) {
        list.get(0).addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"neimplementat");

        });
    }

    public static PostedJobsList getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
