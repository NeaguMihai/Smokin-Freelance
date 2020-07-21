package guiComponents;

import controller.AdminController;
import modelControllerInterfaces.EventController;
import modelControllerInterfaces.FramesController;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TopMenuBar extends JMenuBar implements EventController {

    private FramesController controller;
    private ButtonTarget type;

    private JMenuBar menuBar;
    private JMenu files;
    private JMenuItem refresh;
    private JMenuItem exit;
    private JMenuItem logOut;
    private JMenu helpMenu;
    private JMenuItem help;
    private JMenu admin;
    private JMenuItem adminLogin;
    private JMenuItem normalLogin;

    public TopMenuBar(FramesController controller) {
        this.controller = controller;
        createMenu();
    }
    private void setupMenuItems(JMenu menu, List<JMenuItem> submenus) {
        menuItem(menu);
        submenus.forEach(this::menuItem);
        submenus.forEach(menu::add);
        menuBar.add(menu);
    }

    private void createMenu() {
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setBackground(Color.RED.darker());

        files = new JMenu("Files");
        exit = new JMenuItem("Exit");
        refresh = new JMenuItem("Refresh");
        logOut = new JMenuItem("logOut");

        helpMenu = new JMenu("Help");
        help = new JMenuItem("help");

        admin = new JMenu("Admin");
        adminLogin = new JMenuItem("Admin Login");
        normalLogin = new JMenuItem("Normal Login");

        setupMenuItems(files, Arrays.asList(refresh, logOut,exit));

        setupMenuItems(helpMenu, Collections.singletonList(help));

        setupMenuItems(admin,Arrays.asList(adminLogin, normalLogin));

        buttonFunctionality();

    }
    private void menuItem(JMenuItem jItem) {
        jItem.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        jItem.setForeground(Color.WHITE);
        jItem.setBackground(Color.RED.darker());
    }

    public void buttonFunctionality() {
        exit.addActionListener(e -> controller.closeApp());
        adminLogin.addActionListener(e -> {
            type = ButtonTarget.ADMIN;
            changeLoginType(controller);
        });
        normalLogin.addActionListener(e -> {
            type = ButtonTarget.LOGIN;
            AdminController.getInstance().normalConnection();
            changeLoginType(controller);
        });

        logOut.addActionListener(e -> {
            type = ButtonTarget.LOGIN;
            AdminController.getInstance().normalConnection();
            changeLoginType(controller);
        });
        refresh.addActionListener(e -> updateFrame(controller));

        help.addActionListener(e -> new HelpPanel(100,100));
    }

    @Override
    public void changeLoginType(FramesController controller) {
        controller.switchFrameTo(type);


    }

    @Override
    public void updateFrame(FramesController controller) {
        controller.refreshPage();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
