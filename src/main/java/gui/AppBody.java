package gui;


import guiComponents.AbstractPanel;
import guiComponents.ButtonTarget;
import guiComponents.UserMenu;
import model.AppUserModel;
import modelControllerInterfaces.FramesController;

import javax.swing.*;
import java.awt.*;

public class AppBody extends AbstractPanel {
    private JPanel panel1;
    private JButton button1;
    private JLabel UserName;
    private JLabel money;
    private JLabel level;
    private UserMenu userMenu;

    public AppBody(FramesController manager, Point point) {

        super(manager);
       UserName.setText(AppUserModel.getInstance().getName());
       money.setText(AppUserModel.getInstance().getMoney() + "$" );
       level.setText(String.valueOf(AppUserModel.getInstance().getLevel()));
       userMenu = new UserMenu(button1,this , point);
       buttonFunctionality();
    }



    public void buttonFunctionality() {
        button1.addActionListener(e -> {
                userMenu.getPopupMenu().show(button1, button1.getWidth()/2, button1.getHeight()/2);
        });


    }

    @Override
    public void linkButtonAction() {
        getManager().switchFrameTo(ButtonTarget.LOGIN);
    }

    public JPanel getPanel() {
        return panel1;
    }

    @Override
    public void refresh() {
        money.setText(AppUserModel.getInstance().getMoney() + "$" );
        level.setText(String.valueOf(AppUserModel.getInstance().getLevel()));
    }
}