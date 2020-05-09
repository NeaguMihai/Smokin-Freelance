package guiComponents;

import AccountConfig.ReadWrite;
import controller.UserController;
import gui.AppBody;
import model.AppUserModel;
import model.FriendList;
import model.FriendModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMenu {

    private JPopupMenu popupMenu;
    private List<JMenuItem> dropdownButtons;
    private JPopupMenu friendlist;
    private FriendList friends;

    public UserMenu(JButton button, AppBody appBody) {
        friends = FriendList.getInstance();
        createDropdown();
        ReadWrite.ListHolder holder = ReadWrite.getInstance().restoreFriendConfig();
        friends.createFriendList(friendlist, holder);
        buttonFunctionality(button, appBody);
    }

    public void createDropdown() {
        popupMenu = new JPopupMenu("Options");
        friendlist = new JPopupMenu("friendlist");

        dropdownButtons = new LinkedList<>();

        dropdownButtons.add(new JMenuItem("Delete Account"));
        dropdownButtons.add(new JMenuItem("Add friend"));
        dropdownButtons.add(new JMenuItem("Friend list"));

        dropdownButtons.forEach(e -> {
            e.setBackground(Color.RED.darker());
            e.setForeground(Color.WHITE);
            e.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        });
        dropdownButtons.forEach(popupMenu::add);

    }



    public void buttonFunctionality(JButton button, AppBody appBody) {
        dropdownButtons.get(0).addActionListener(e -> deleteAccount(appBody));
        dropdownButtons.get(1).addActionListener(e -> {
            String friendName = JOptionPane.showInputDialog("Please input the friend mail adress");
            UserController.getInstance().searchFriend(friendName);
            friends.addFriend();
            popupMenu.setVisible(false);
        });
        dropdownButtons.get(2).addActionListener(e -> {

            friendlist.show(button, button.getWidth()/2, button.getHeight()/2);

        });

    }

    public void deleteAccount(AppBody app) {
        UserController.getInstance()
                .deleteAccount(AppUserModel.getInstance());
        app.linkButtonAction();
        popupMenu.setVisible(false);
    }



    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }
}
