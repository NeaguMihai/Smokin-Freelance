package gui;


import controller.AdminController;
import guiComponents.AbstractPanel;
import guiComponents.ButtonTarget;
import modelControllerInterfaces.FramesController;
import guiComponents.TopMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LandPage extends JFrame implements FramesController, ActionListener {

    private TopMenuBar menuBar;

    private AbstractPanel currentPanel;


    private static class SingletonHolder {
        private static final LandPage INSTANCE = new LandPage();
    }

    private LandPage() {
        super("Smokin'Exchange");

        menuBar = new TopMenuBar(this);

        setJMenuBar(menuBar.getMenuBar());

        switchFrameTo(ButtonTarget.LOGIN);
//        pack();
        setSize(new Dimension(400,800));
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
                break;
            case ADMINFRAME:
                currentPanel = new AdminFrame(this, new Point(this.getX(),this.getY()));
                setContentPane(((AdminFrame)currentPanel).getPanel());
                break;
            case APPBODY:
                currentPanel = new AppBody(this,new Point(this.getX(),this.getY()));
                setContentPane(((AppBody)currentPanel).getPanel());
                break;
        }
        refreshFrame();

    }

    @Override
    public void closeApp() {
        dispose();
    }

    @Override
    public void refreshPage() {
        currentPanel.refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
