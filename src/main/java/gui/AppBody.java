package gui;

import guiComponents.AbstractPanel;
import modelControllerInterfaces.FramesController;

import javax.swing.*;
import java.util.List;

public class AppBody extends AbstractPanel {
    private JPanel panel1;
    private JButton button1;

//    private List<Contacts> contacts;

    public AppBody(FramesController manager) {
        super(manager);
    }


    @Override
    public void linkButtonAction() {

    }

    public JPanel getPanel() {
        return panel1;
    }

    @Override
    public void refresh() {

    }
}
