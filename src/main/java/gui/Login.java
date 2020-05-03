package gui;

import javax.swing.*;

public class Login {
    private JPanel panel1;
    private JButton login;
    private JTextField email;
    private JPasswordField password;
    private JLabel mailAdress;
    private JButton linkRegister;

    public Login() {
        buttonFunctionality();
    }

    public JPanel getPanel() {

        return panel1;
    }

    private void buttonFunctionality() {
        linkRegister.addActionListener(e -> LandPage.changeContent());
    }
}
