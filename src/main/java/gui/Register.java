package gui;

import guiComponents.AbstractPanel;
import guiComponents.ButtonTarget;
import modelControllerInterfaces.FramesController;

import javax.swing.*;
import java.util.Arrays;

public class Register extends AbstractPanel {
    private JPanel panel1;
    private JButton register;
    private JTextField name;
    private JTextField email;
    private JPasswordField password;
    private JLabel mailAdress;
    private JButton linkLogin;
    private JLabel background;

    public Register(FramesController manager) {
        super(manager);
        buttonFunctionality();
        roundJTextField(Arrays.<JTextField>asList(password,email,name));

    }

    public JPanel getPanel() {

        return panel1;
    }

    private void buttonFunctionality() {
        linkLogin.addActionListener(e ->linkButtonAction());

    }



    @Override
    public void linkButtonAction() {
        getManager().switchFrameTo(ButtonTarget.LOGIN);
    }

    @Override
    public void refresh() {
        name.setText("");
        email.setText("");
        password.setText("");
    }
}
