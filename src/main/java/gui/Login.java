package gui;

import controller.AbstractPanel;
import controller.ButtonTarget;
import controller.FramesController;

import javax.swing.*;

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
}
