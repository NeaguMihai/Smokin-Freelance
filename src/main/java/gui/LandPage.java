package gui;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandPage extends JFrame {

    private JMenuBar menuBar;
    private JMenu files;
    private JMenuItem exit;
    private JMenuItem reconnect;
    private JMenu helpMenu;
    private JMenuItem help;


    private static JPanel panel;
    private static JFrame frame;


    private static Register register;
    private static Login login;

    private static class SingletonHolder {
        public static final LandPage INSTANCE = new LandPage();
    }
    public LandPage() {
        super("Smokin'Chats");
        frame = this;

        register = new Register();
        panel = register.getPanel();
        setContentPane(panel);
        createMenu();

        setJMenuBar(menuBar);
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMenu() {
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setBackground(Color.RED.darker());

        files = new JMenu("Files");
        exit = new JMenuItem("Exit");
        reconnect = new JMenuItem("Reconnect");

        helpMenu = new JMenu("Help");
        help = new JMenuItem("help");

        menuItem(files);
        menuItem(exit);
        menuItem(reconnect);

        menuItem(helpMenu);
        menuItem(help);

        buttonFunctionality();

        files.add(reconnect);
        files.add(exit);
        helpMenu.add(help);

        menuBar.add(files);
        menuBar.add(helpMenu);

    }

    public void buttonFunctionality() {
        exit.addActionListener(e -> dispose());
    }

    private void menuItem(JMenuItem jItem) {
        jItem.setFont(new Font(Font.MONOSPACED,Font.PLAIN,17));
        jItem.setForeground(Color.WHITE);
        jItem.setBackground(Color.RED.darker());
    }

    public static void changeContent() {
        if (panel != null) {
            if (panel == register.getPanel()) {
                login = new Login();
                panel = login.getPanel();

                frame.setContentPane(panel);
            } else if(panel == login.getPanel()) {
                register = new Register();
                panel = register.getPanel();
                frame.setContentPane(panel);
            }

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI(frame);
        }
    }

    public static LandPage getInstance() {
        return SingletonHolder.INSTANCE;
    }



}
