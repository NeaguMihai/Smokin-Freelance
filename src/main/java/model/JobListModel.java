package model;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class JobListModel {

    private List<JMenu> jobs;
    private JPopupMenu parent;


    public JobListModel() {

    }



    public void createJobList(JPopupMenu joblist, LinkedList<JMenu> list2) {

        jobs = list2;
        parent = joblist;
        joblist.removeAll();

        parent.setBackground(Color.RED.darker());

        jobs.forEach((v) -> {
            v.setForeground(Color.WHITE);
            v.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        });

        jobs.forEach(parent::add);
        jobs.forEach(this::JobListAction);
        parent.pack();


    }


    public void JobListAction(JMenu menu) {

    }

    protected void addButtonActions(List<JMenuItem> list, JMenu menu) {
        list.get(1).addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"neimplementat");

        });

        list.get(0).addActionListener(e -> {

            JOptionPane.showMessageDialog(null,"neimplementat");

        });

    }

}
