package gui;

import guiComponents.AbstractPanel;
import guiComponents.ButtonTarget;
import modelControllerInterfaces.FramesController;

import javax.swing.*;
import java.util.Arrays;

public class Login extends AbstractPanel {
    private JPanel panel1;
    private JButton login;
    private JTextField email;
    private JPasswordField password;
    private JLabel mailAdress;
    private JButton linkRegister;

    public Login(FramesController manager)
    {   super(manager);
        buttonFunctionality();
        roundJTextField(Arrays.<JTextField>asList(email,password));

    }

    public JPanel getPanel() {

        return panel1;
    }

    private void buttonFunctionality() {
        linkRegister.addActionListener(e -> linkButtonAction());
    }

    @Override
    public void linkButtonAction() {
        getManager().switchFrameTo(ButtonTarget.REGISTER);
    }

    @Override
    public void refresh() {
        email.setText("");
        password.setText("");
    }
}
