package gui;

import controller.AdminController;
import guiComponents.AbstractPanel;
import modelControllerInterfaces.FramesController;
import guiComponents.ButtonTarget;

import javax.swing.*;
import java.util.Arrays;

public class AdminLogin extends AbstractPanel {

    private JPanel panel1;
    private JButton login;
    private JTextField user;
    private JPasswordField password;
    private JLabel mailAdress;

    public AdminLogin(FramesController manager) {
        super(manager);
        buttonFunctionality();
        roundJTextField(Arrays.<JTextField>asList(user,password));
    }

    public JPanel getPanel() {

        return panel1;
    }

    private void buttonFunctionality() {
        login.addActionListener(e -> {
            if (!AdminController.getInstance().adminLogin(user.getText(),new String(password.getPassword()))) {
                JOptionPane.showMessageDialog(null, "Wrong username or password");
            }else {
                linkButtonAction();
            }

        });
    }


    @Override
    public void linkButtonAction() {
        getManager().switchFrameTo(ButtonTarget.ADMINFRAME);
    }

    @Override
    public void refresh() {
        user.setText("");
        password.setText("");
    }


}
