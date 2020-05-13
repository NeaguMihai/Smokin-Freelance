package guiComponents;

import controller.UserController;
import gui.AppBody;
import model.AppUserModel;
import model.JobList;

import model.PostedJobsList;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;


public class UserMenu {

    private JPopupMenu popupMenu;
    private List<JMenuItem> dropdownButtons;
    private JPopupMenu joblist;
    private JPopupMenu postedJobList;
    private JobList jobs;
    private PostedJobsList postedJobs;
    private Point point;

    public UserMenu(JButton button, AppBody appBody, Point point) {
        postedJobs = PostedJobsList.getInstance();
        jobs = JobList.getInstance();
        this.point = point;
        createDropdown();
        postedJobs.createJobList(postedJobList);
        jobs.createJobList(joblist);
        buttonFunctionality(button, appBody);
    }

    private void createDropdown() {
        popupMenu = new JPopupMenu("Options");
        joblist = new JPopupMenu("joblist");
        postedJobList = new JPopupMenu("postedJobList");

        dropdownButtons = new LinkedList<>();

        dropdownButtons.add(new JMenuItem("Delete Account"));
        dropdownButtons.add(new JMenuItem("Post Job"));
        dropdownButtons.add(new JMenuItem("Search for a job"));
        dropdownButtons.add(new JMenuItem("Job list"));
        dropdownButtons.add(new JMenuItem("My posted jobs"));

        dropdownButtons.forEach(e -> {
            e.setBackground(Color.RED.darker());
            e.setForeground(Color.WHITE);
            e.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        });
        dropdownButtons.forEach(popupMenu::add);

    }



    private void buttonFunctionality(JButton button, AppBody appBody) {
        dropdownButtons.get(0).addActionListener(e -> deleteAccount(appBody));
        dropdownButtons.get(1).addActionListener(e -> {
            new AddJobPanel(point.x + 200, point.y, appBody);

        });
        dropdownButtons.get(2).addActionListener(e -> {
            new JobSearch(point.x+200, point.y);
        });
        dropdownButtons.get(3).addActionListener(e -> {

            joblist.show(button, button.getWidth()/2, button.getHeight()/2);

        });
        dropdownButtons.get(4).addActionListener(e -> {

            postedJobList.show(button, button.getWidth()/2, button.getHeight()/2);

        });

    }

    public void deleteAccount(AppBody app) {
        String s = JOptionPane.showInputDialog(null, "You really want to delete this account?");
        if (s.equals("yes")) {
            UserController.getInstance()
                    .deleteAccount(AppUserModel.getInstance());
            app.linkButtonAction();
            popupMenu.setVisible(false);
        }
    }



    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }
}
