package gui;

import controller.AdminController;
import guiComponents.LogPanel;
import modelControllerInterfaces.FramesController;
import guiComponents.AbstractPanel;
import guiComponents.AdminContacts;
import model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdminFrame extends AbstractPanel {
    private JPanel panel1;
    private JScrollPane scroll;
    private JPanel container;
    private JButton viewLogsButton;
    private  Point point;

    private List<AdminContacts> model;

    AdminFrame(FramesController manager, Point point) {
        super(manager);
        model = new ArrayList<>();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        this.point = point;
        createContext(point.x, point.y);

    }

    private void listUsers() {
        List<UserModel> unregisteredUsers = AdminController.getInstance().getUnregisters();

        model.clear();

        unregisteredUsers.forEach(e -> {
           AdminContacts contacts = new AdminContacts(e, this.getManager());
           model.add(contacts);
        });
    }

    private void createContext(int x, int y) {
        listUsers();
        model.forEach(e -> container.add(e.getPanel()));
        container.revalidate();
        container.repaint();
        viewLogsButton.addActionListener(e -> new LogPanel(x, y));

    }

    JPanel getPanel() {
        return panel1;
    }

    @Override
    public void linkButtonAction() {

    }

    @Override
    public void refresh() {
        container.removeAll();
        createContext(point.x, point.y);
        System.out.println(model.size());

    }
}
