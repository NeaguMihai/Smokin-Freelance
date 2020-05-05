package gui;

import controller.AdminController;
import guiComponents.AbstractPanel;
import controller.FramesController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!AdminController.getInstance().adminLogin(user.getText(),new String(password.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Wrong username or password");
                }
            }
        });
    }


    @Override
    public void linkButtonAction() {

    }


}
