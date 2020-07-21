package guiComponents;

import algorithms.FormatText;
import controller.JobController;
import controller.UserController;
import gui.AppBody;
import model.AppUserModel;

import javax.swing.*;

import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobListItem {
    private JPanel panel1;
    private JButton moreButton;
    private JLabel money;
    private JLabel name;
    private JLabel level;
    private JButton acceptButton;
    private JPopupMenu popupMenu;
    private int id;
    private int posterId;


    public JobListItem(AppBody appBody, int id, String name, int money, int level, String details) {
        this.id = id;
        this.name.setText(name);
        this.money.setText(money + "$");
        this.level.setText(level + "");
        String str = FormatText.getFormatedText(details);
        detailsButton(str);
        setAcceptButton(appBody);
        moreButton.addActionListener(e -> {
            popupMenu.show(moreButton, moreButton.getWidth()/2, moreButton.getHeight()/2);
        });
        panel1.setSize(550,50);
    }



    public JPanel getPanel1() {
        return panel1;
    }

    private void detailsButton(String str) {
        popupMenu = new JPopupMenu("popup");
        JTextPane pane = new JTextPane();
        pane.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        pane.setText(str);
        popupMenu.add(pane);
    }

    public void setAcceptButton(AppBody appBody) {
        acceptButton.addActionListener(e -> {
            if (AppUserModel.getInstance().getLevel() >= Integer.parseInt(level.getText())){
                JobController.getInstance().occupyJob(this.id);
                String str = AppUserModel.getInstance().getJobs();
                if (str.length() == 0)
                    AppUserModel.getInstance().setJobs(this.id + "");
                else
                    AppUserModel.getInstance().setJobs(str + "," + this.id);
                UserController.getInstance().updateJobs(AppUserModel.getInstance().getJobs());
                acceptButton.setEnabled(false);
            }
            else
                JOptionPane.showMessageDialog(null, "Your level is too small for this job");

            ExecutorService service = Executors.newSingleThreadExecutor();

            service.execute(appBody.getTask());
        });
    }
}
