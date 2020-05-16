package guiComponents;

import algorithms.FormatText;
import controller.JobController;
import controller.UserController;
import gui.AppBody;
import model.AppUserModel;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
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

    public AcceptedJobPanel(int id, String name, String level, String money, String details, AppBody appBody) {
        this.id = id;
        this.name.setText(name);
        this.level.setText(level);
        this.money.setText(money);
        this.textPane1.setText(FormatText.getFormatedText(details));
        this.appBody = appBody;
        buttonFunctionality();
    }

    public JPanel getPanel1() {
        return panel1;
    }


    private void buttonFunctionality() {
        giveUpButton.addActionListener(e -> {
            List<Integer> tokens = Arrays.asList(AppUserModel
                    .getInstance()
                    .getJobs().split(",")).stream()
                    .map(Integer::parseInt).collect(Collectors.toList());
            tokens.forEach(System.out::println);
            String res = tokens.stream()
                    .filter(ev -> ev == id)
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));

            System.out.println(res);
            AppUserModel.getInstance().setJobs(res);

            UserController.getInstance().updateJobs(res);

            appBody.createJobList();
            appBody.refresh();

        });
    }

}
