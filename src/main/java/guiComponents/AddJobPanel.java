package guiComponents;

import algorithms.IsNumber;
import controller.JobController;
import controller.UserController;
import gui.AppBody;
import model.AppUserModel;
import model.HistoryHolder;

import javax.swing.*;

public class AddJobPanel extends JFrame {
    private JTextField jobName;
    private JPanel panel1;
    private JTextField money;
    private JTextField level;
    private JButton postJobButton;
    private JTextPane description;

    public AddJobPanel(int x, int y, AppBody appBody) {
        setContentPane(panel1);
        setLocation(x + 200,y);
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addButtonFunctionality(appBody);
        setVisible(true);
    }


    public void sendData(AppBody appBody) {
        if (jobName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "You must complete all fields");
            return;

        }else if (!IsNumber.isInteger(money.getText())) {
            JOptionPane.showMessageDialog(null, "You must use only numbers in the money field");
            return;
        }else if (!IsNumber.isInteger(level.getText())) {
            JOptionPane.showMessageDialog(null, "You must use only numbers in the level field");
            return;
        }else if (Integer.parseInt(level.getText())>10||Integer.parseInt(level.getText())<3) {
            JOptionPane.showMessageDialog(null, "The difficulty level must be between 3 and 10");
            return;
        }else if (Integer.parseInt(money.getText()) >= AppUserModel.getInstance().getMoney()) {
            JOptionPane.showMessageDialog(null, "Insufficient funds");
            return;
        }

        AppUserModel.getInstance().setMoney(AppUserModel.getInstance().getMoney() - Integer.parseInt(money.getText()));
        UserController.getInstance().updateMoney(AppUserModel.getInstance().getMoney());
        appBody.refresh();

        JobController.getInstance().addJob(
                jobName.getText(),
                Integer.parseInt(money.getText()),
                Integer.parseInt(level.getText()),
                description.getText()


        );
        AppUserModel.getInstance();
        JOptionPane.showMessageDialog(null, "The job was successfully posted");
        dispose();
    }

    private void addButtonFunctionality(AppBody appBody) {
        postJobButton.addActionListener(e -> sendData(appBody));
    }

}
