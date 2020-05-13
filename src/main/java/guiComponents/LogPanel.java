package guiComponents;

import controller.AdminController;
import gui.AdminFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LogPanel extends JFrame{
    private JPanel panel1;
    private JTextPane textPane1;

    public LogPanel(int x, int y) {

        setContentPane(panel1);
        setSize(600,800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Log window");
        setLocation(x + 300, y);
        setVisible(true);
        prepareLogs();
    }

    private void prepareLogs() {
        List<String> logs = AdminController.getInstance().getLogs();
        String text ="";
        StringBuilder sb = new StringBuilder(text);
        logs.forEach(e -> sb.append(e).append("\n"));
        textPane1.setText(sb.toString());
    }
}
