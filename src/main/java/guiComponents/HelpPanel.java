package guiComponents;

import algorithms.ReadWrite;
import controller.AdminController;
import gui.AdminFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HelpPanel extends JFrame{
    private JPanel panel1;
    private JTextPane textPane1;

    public HelpPanel(int x, int y) {

        setContentPane(panel1);
        setSize(600,800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Log window");
        setLocation(x + 300, y);
        setVisible(true);
        prepareText();
    }

    private void prepareText() {
        String logs = ReadWrite.getHelpText();

        textPane1.setText(logs);
    }
}
