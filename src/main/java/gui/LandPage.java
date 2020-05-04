package gui;


import controller.AbstractPanel;
import controller.ButtonTarget;
import controller.FramesController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class LandPage extends JFrame implements FramesController {

    private TopMenuBar menuBar;

    private AbstractPanel currentPanel;

    private static class SingletonHolder {
        private static final LandPage INSTANCE = new LandPage();
    }

    private LandPage() {
        super("Smokin'Chats");

        menuBar = new TopMenuBar(this);

        setJMenuBar(menuBar.getMenuBar());

        switchFrameTo(ButtonTarget.LOGIN);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void switchFrameTo(ButtonTarget target) {

        switch (target) {
            case LOGIN:
                currentPanel = new Login(this);
                setContentPane(((Login)currentPanel).getPanel());
                break;
            case REGISTER:
                currentPanel = new Register(this);
                setContentPane(((Register)currentPanel).getPanel());
                break;
            case ADMIN:
                currentPanel = new AdminLogin(this);
                setContentPane(((AdminLogin)currentPanel).getPanel());
        }
        refreshFrame();

    }

    @Override
    public void closeApp() {
        dispose();
    }

    //metoda care da refresh la Jframe dupa schimbarea Jpanelului
    //Am pus Thread.sleep ca schimbarea sa nu fie prea brusca
    private void refreshFrame() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    //Metoda care intoarce instanta de Frame
    public static LandPage getInstance() {
        return SingletonHolder.INSTANCE;
    }



}
