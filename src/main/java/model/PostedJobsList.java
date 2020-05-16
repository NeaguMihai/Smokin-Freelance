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
        list.get(1).addActionListener(e -> {
            JobController.getInstance().deleteJob(id);
            JOptionPane.showMessageDialog(null,"Deleted successfully");

        });
    }

    public static PostedJobsList getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
