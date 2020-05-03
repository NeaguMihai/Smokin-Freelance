package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
    private JPanel panel1;
    private JButton register;
    private JTextField name;
    private JTextField email;
    private JPasswordField password;
    private JLabel mailAdress;
    private JButton buttonLogin;

    public Register() {
        buttonFunctionality();
    }

    public JPanel getPanel() {

        return panel1;
    }

    private void buttonFunctionality() {

      buttonLogin.addActionListener(e -> LandPage.changeContent());
    }
}
