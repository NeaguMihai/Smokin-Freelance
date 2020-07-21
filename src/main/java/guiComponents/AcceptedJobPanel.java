package guiComponents;

import algorithms.FormatText;
import controller.ConnectionManager;
import controller.JobController;
import controller.UserController;
import gui.AppBody;
import model.AppUserModel;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class AcceptedJobPanel{
    private int id;
    private JPanel panel1;
    private JButton giveUpButton;
    private JButton finishJobButton;
    private JLabel name;
    private JLabel level;
    private JLabel money;
    private JTextPane textPane1;
    private AppBody appBody;
    private ExecutorService service = Executors.newSingleThreadExecutor();


    public AcceptedJobPanel(int id, String name, String level, String money, String details, AppBody appBody) {
        this.id = id;
        this.name.setText(name);
        this.level.setText(level);
        this.money.setText(money);
        this.textPane1.setText(FormatText.getFormatedText(details));
        this.appBody = appBody;
        giveUpButtonFunction();
        finishJobButtonFunction();
    }

    public JPanel getPanel1() {
        return panel1;
    }


    private void giveUpButtonFunction() {
        giveUpButton.addActionListener(e -> {

            removeJob();

            UserController.getInstance().decreaseLevel();

            JobController.getInstance().freeJob(id);

            service.execute(appBody.getTask());

        });
    }

    private void finishJobButtonFunction() {
        finishJobButton.addActionListener(e -> {

            removeJob();
            JobController.getInstance().finishJob(id);
            UserController.getInstance().increaseLevel();
            JobController.getInstance().finishAndPay(id);

            service.execute(appBody.getTask());
        });
    }

    private void removeJob() {
        panel1.getParent().remove(panel1);
        List<Integer> tokens = Arrays.stream(AppUserModel
                .getInstance()
                .getJobs().split(","))
                .map(Integer::parseInt).collect(Collectors.toList());


        String res =  tokens.stream()
                .filter(ev -> ev != id)
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        AppUserModel.getInstance().setJobs(res);
        UserController.getInstance().updateJobs(res);




    }

}
