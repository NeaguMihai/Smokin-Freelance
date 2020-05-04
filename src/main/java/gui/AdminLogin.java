package gui;

import guiComponents.AbstractPanel;
import controller.FramesController;

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

    }

    @Override
    public void linkButtonAction() {

    }


}
