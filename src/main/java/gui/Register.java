package gui;

import algorithms.PasswordEncrypt;
import controller.UserController;
import guiComponents.AbstractPanel;
import guiComponents.ButtonTarget;
import model.UserModel;
import modelControllerInterfaces.FramesController;

import javax.swing.*;
import java.util.Arrays;

public class Register extends AbstractPanel{
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
        register.addActionListener(e -> registerRequest());

    }



    public void registerRequest() {
       if (!UserController.getInstance()
               .registerRequest(
                       new UserModel(
                               0,
                               name.getText(),
                               email.getText(),
                               PasswordEncrypt.getPassword(new String(password.getPassword())),
                               "",
                               0
                       )
               )
       ) {
           JOptionPane.showMessageDialog(null,"User Already registered");
           Arrays.asList(email, name, password).forEach(e -> e.setText(""));
       }else {
           linkButtonAction();
       }

    }

    @Override
    public void keyEnterTrigger() {
        registerRequest();
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
