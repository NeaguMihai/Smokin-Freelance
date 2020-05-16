package guiComponents;

import algorithms.IsNumber;
import controller.UserController;
import gui.AppBody;
import model.AppUserModel;

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
    private PostedJobsList postedJobs;
    private Point point;

    public UserMenu(JButton button, AppBody appBody, Point point) {
        postedJobs = PostedJobsList.getInstance();
        this.point = point;
        createDropdown();
        postedJobs.createJobList(postedJobList);
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
        dropdownButtons.add(new JMenuItem("My posted jobs"));
        dropdownButtons.add(new JMenuItem("Top up account"));

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
            new JobSearch(point.x+200, point.y, appBody);
        });

        dropdownButtons.get(3).addActionListener(e -> {
            postedJobs.createJobList(postedJobList);
            postedJobList.show(button, button.getWidth()/2, button.getHeight()/2);

        });
        dropdownButtons.get(4).addActionListener(e -> {
            String cardNr = JOptionPane.showInputDialog("Insert card number");
            if (cardNr.length() != 16 || !IsNumber.isLong(cardNr)) {
                JOptionPane.showMessageDialog(null,"Wrong data");
                return;
            }


            String cvv = JOptionPane.showInputDialog("Insert CVV");
            if (cvv.length() !=3 || !IsNumber.isInteger(cvv)){
                JOptionPane.showMessageDialog(null,"Wrong data");
                return;
            }

            String sum = JOptionPane.showInputDialog("Insert sum");
            if (sum.equals("") || !IsNumber.isInteger(sum)) {
                JOptionPane.showMessageDialog(null,"Wrong data");
                return;
            }
            AppUserModel.getInstance().setMoney(AppUserModel.getInstance().getMoney() + Integer.parseInt(sum));
            UserController.getInstance().updateMoney(AppUserModel.getInstance().getMoney());
            appBody.refresh();
            JOptionPane.showMessageDialog(null,"Successful");

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

    public void reftesh() {

    }
}
