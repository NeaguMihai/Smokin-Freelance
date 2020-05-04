package gui;

import controller.AbstractPanel;
import controller.ButtonTarget;
import controller.FramesController;

import javax.swing.*;

public class AdminLogin extends AbstractPanel {

    private JPanel panel1;
    private JButton login;
    private JTextField user;
    private JPasswordField password;
    private JLabel mailAdress;

    public AdminLogin(FramesController manager) {
        super(manager);
        buttonFunctionality();
    }

    public JPanel getPanel() {

        return panel1;
    }

    private void buttonFunctionality() {

    }

    @Override
    public void linkButtonAction() {

    }
}
