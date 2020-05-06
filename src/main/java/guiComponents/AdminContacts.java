package guiComponents;

import controller.AdminController;
import model.UserModel;
import modelControllerInterfaces.EventController;
import modelControllerInterfaces.FramesController;

import javax.swing.*;

public class AdminContacts implements EventController{

    private JPanel panel;
    private JButton acceptButton;
    private JButton rejectButton;
    private JLabel email;
    private JLabel name;
    private UserModel user;
    private FramesController controller;

    public AdminContacts(UserModel user, FramesController controller) {
        this.controller = controller;
        this.user = user;
        name.setText(user.getName());
        email.setText(user.getEmail());
        buttonFunctionality();
    }

    public void buttonFunctionality() {
        rejectButton.addActionListener(e-> AdminController.getInstance().deleteRequest(this));


    }

    public JPanel getPanel() {
        return panel;
    }

    public UserModel getUser() {
        return user;
    }


    @Override
    public void changeLoginType(FramesController controller) {

    }

    @Override
    public void updateFrame(FramesController controller) {
        controller.refreshPage();
    }

    public FramesController getController() {
        return controller;
    }
}
