package gui;

import algorithms.PasswordEncrypt;
import controller.UserController;
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

    private void loginRequest() {

        if (UserController.getInstance()
                .loginRequest(
                        email.getText(),
                        PasswordEncrypt.getPassword(new String(password.getPassword())))
        ) {

            getManager().switchFrameTo(ButtonTarget.APPBODY);
        }
        Arrays.asList(email, password).forEach(e -> e.setText(""));
    }

    private void buttonFunctionality() {
        //prin aceasta metoda se trece la fereastra de register
        linkRegister.addActionListener(e -> linkButtonAction());
        //request de login
        login.addActionListener(e -> loginRequest());
    }

    @Override
    public void keyEnterTrigger() {
        loginRequest();
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
