package gui;


import algorithms.FormatText;
import algorithms.UpdateJobsGuiTask;
import controller.UserController;
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
import java.util.concurrent.*;

public class AppBody extends AbstractPanel {
    private JPanel panel1;
    private JButton settings;
    private JLabel UserName;
    private JLabel money;
    private JLabel level;
    private JPanel container;
    private UserMenu userMenu;
    private Semaphore semaphore = new Semaphore(1);
    private UpdateJobsGuiTask task;

    public AppBody(FramesController manager, Point point) {

        super(manager);
       UserName.setText(AppUserModel.getInstance().getName());
       money.setText(AppUserModel.getInstance().getMoney() + "$" );
       level.setText(String.valueOf(AppUserModel.getInstance().getLevel()));
       userMenu = new UserMenu(settings,this , point);

       container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

       //un thread care updateaza la fiecare 5 minute lista de joburi acceptate, banii si nivelul userului in interfata grafica
       ScheduledExecutorService backgroundService = Executors.newSingleThreadScheduledExecutor();

       task = new UpdateJobsGuiTask(this);

       backgroundService.scheduleWithFixedDelay(task,0,5,TimeUnit.MINUTES);


       buttonFunctionality();
    }



    private void buttonFunctionality() {
        settings.addActionListener(e -> {
            //atunci cand se apasa pe buton se creaza un JPopUpMenu ce are coltul din stanga la jumatatea butonului settings
                userMenu.getPopupMenu().show(settings, settings.getWidth()/2, settings.getHeight()/2);
        });
    }

    //metoda care adauga in fereastra container toate joburile acceptate de user
    public void  createJobList() {

        try {
            semaphore.acquire();
            List<JobModel> jm = AppUserModel.getInstance().getJobList();
            List<AcceptedJobPanel> jobPanels = new LinkedList<>();

            if (jm.size() != 0)
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

            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refresh();

    }


    @Override
    public void linkButtonAction() {
        getManager().switchFrameTo(ButtonTarget.LOGIN);
    }

    public JPanel getPanel() {
        return panel1;
    }

    //metoda care recreaza interfata grafica updatand valorile afisate
    @Override
    public void refresh() {
        List<Integer> list = UserController.getInstance().updateInfo();
        AppUserModel.getInstance().setMoney(list.get(0));
        AppUserModel.getInstance().setLevel(list.get(1));
        money.setText(AppUserModel.getInstance().getMoney() + "$" );
        level.setText(String.valueOf(AppUserModel.getInstance().getLevel()));
        container.revalidate();
        container.repaint();
    }

    public UpdateJobsGuiTask getTask() {
        return task;
    }
}
