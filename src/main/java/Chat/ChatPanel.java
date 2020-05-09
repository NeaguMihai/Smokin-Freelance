package Chat;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ChatPanel extends JFrame {

    private JPanel panel1;
    private JButton BUZZButton;
    private JTextField textField1;
    private JButton sendButton;

    public ChatPanel() {
        setContentPane(panel1);
        setSize(750,650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


    }

    public static void main(String[] args) {
        new ChatPanel();
    }
}
