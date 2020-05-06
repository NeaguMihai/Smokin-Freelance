package gui;

import controller.AdminController;
import modelControllerInterfaces.FramesController;
import guiComponents.AbstractPanel;
import guiComponents.AdminContacts;
import model.UserModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AdminFrame extends AbstractPanel {
    private JPanel panel1;
    private JScrollPane scroll;
    private JPanel container;

    private List<AdminContacts> model;

    public AdminFrame(FramesController manager) {
        super(manager);
        model = new ArrayList<>();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        createContext();

    }

    private void listUsers() {
        List<UserModel> unregisteredUsers = AdminController.getInstance().getUnregisters();

        model.clear();

        unregisteredUsers.forEach(e -> {
           AdminContacts contacts = new AdminContacts(e, this.getManager());
           model.add(contacts);
        });
    }

    private void createContext() {
        listUsers();
        model.forEach(e -> container.add(e.getPanel()));
        container.revalidate();
        container.repaint();

    }

    public JPanel getPanel() {
        return panel1;
    }

    @Override
    public void linkButtonAction() {

    }

    @Override
    public void refresh() {
        container.removeAll();
        createContext();
        System.out.println(model.size());

    }
}
