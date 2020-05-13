package model;

import controller.JobController;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class JobList extends JobListModel {


    public JobList() {
    }

    private static final class SingletonHolder {
        private static final JobList INSTANCE = new JobList();
    }

    public void createJobList(JPopupMenu joblist) {
        LinkedList<JMenuItem> jobAction = new LinkedList<>();
        jobAction.add(new JMenuItem("Give up job"));
        jobAction.add(new JMenuItem("Complete job"));

        super.createJobList(joblist, jobAction, new LinkedList<>());
    }

    @Override
    protected void addButtonActions(List<JMenuItem> list, JMenu menu) {
        super.addButtonActions(list, menu);
    }

    public static JobList getInstance() {
        return SingletonHolder.INSTANCE;
    }


}
