package gui;


import algorithms.FormatText;
import algorithms.UpdateJobsGuiTask;
import guiComponents.AbstractPanel;
import guiComponents.AcceptedJobPanel;
import guiComponents.ButtonTarget;
import guiComponents.UserMenu;
import model.AppUserModel;
import model.JobModel;
import modelControllerInterfaces.FramesController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AppBody extends AbstractPanel {
    private JPanel panel1;
    private JButton button1;
    private JLabel UserName;
    private JLabel money;
    private JLabel level;
    private JPanel container;
    private UserMenu userMenu;
    private ScheduledExecutorService service;

    public AppBody(FramesController manager, Point point) {

        super(manager);
       UserName.setText(AppUserModel.getInstance().getName());
       money.setText(AppUserModel.getInstance().getMoney() + "$" );
       level.setText(String.valueOf(AppUserModel.getInstance().getLevel()));
       userMenu = new UserMenu(button1,this , point);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        service = Executors.newSingleThreadScheduledExecutor();

        UpdateJobsGuiTask task = new UpdateJobsGuiTask(this);

        service.scheduleWithFixedDelay(task,0,5,TimeUnit.MINUTES);


        buttonFunctionality();
    }



    private void buttonFunctionality() {
        button1.addActionListener(e -> {
                userMenu.getPopupMenu().show(button1, button1.getWidth()/2, button1.getHeight()/2);
        });
    }

    public void createJobList() {

        List<JobModel> jm = AppUserModel.getInstance().getJobList();

        List<AcceptedJobPanel> jobPanels = new LinkedList<>();

        jm.forEach(e -> jobPanels.add(new AcceptedJobPanel(
                e.getId(),
                e.getName(),
                String.valueOf(e.getLevel()),
                e.getMoney() + "$",
                FormatText.getFormatedText(e.getDetails()),
                this
        )));
        container.removeAll();
        refresh();
        jobPanels.forEach(e -> container.add(e.getPanel1()));
        refresh();

    }


    @Override
    public void linkButtonAction() {
        getManager().switchFrameTo(ButtonTarget.LOGIN);
    }

    public JPanel getPanel() {
        return panel1;
    }

    @Override
    public void refresh() {
        money.setText(AppUserModel.getInstance().getMoney() + "$" );
        level.setText(String.valueOf(AppUserModel.getInstance().getLevel()));

        container.revalidate();
        container.repaint();
    }
}
