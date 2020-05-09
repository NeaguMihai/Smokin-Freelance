package model;

import AccountConfig.ReadWrite;
import controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class FriendList implements Serializable {

    private Map<String, JMenu> friends;
    private List<JMenuItem> friendActions;
    private JPopupMenu parent;

    private FriendList(Map<String, JMenu> friendList) {
        this.friends = friendList;
    }

    private FriendList() {

    }

    private static final class SingletonHolder {
        private static final FriendList INSTANCE = new FriendList();
    }

    public static FriendList getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void createFriendList(JPopupMenu friendlist, ReadWrite.ListHolder holder) {
        friends = new HashMap<>();
        parent = friendlist;

        friendlist.setBackground(Color.RED.darker());

        List<FriendModel> list = AppUserModel.getInstance().getFriends();
        Optional<List<String>> mails = Optional.ofNullable(holder.getMails());
        Optional<List<String>> customNames = Optional.ofNullable(holder.getCustomNames());

        if (customNames.isEmpty()||mails.isEmpty()) {
            list.forEach(e -> friends.put(e.getMail(),new JMenu(e.getMail())));
        } else {

            for (int i = 0; i < list.size(); i++) {
                if (mails.get().size()>i) {

                    if (list.get(i).getMail().equals(mails.get().get(i))) {


                        friends.put(list.get(i).getMail(), new JMenu(customNames.get().get(i)));
                    }else {
                        friends.put(list.get(i).getMail(), new JMenu(list.get(i).getMail()));
                    }
                }else {
                    friends.put(list.get(i).getMail(), new JMenu(list.get(i).getMail()));
                }
            }
        }


        friends.forEach((k,v) -> {
            v.setForeground(Color.WHITE);
            v.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        });

        friends.forEach(friendlist::add);
        friends.forEach((k,v) -> friendListAction(v).forEach(v::add));
        friendlist.pack();


    }


    public List<JMenuItem> friendListAction(JMenu menu) {
        friendActions = new LinkedList<>();
        friendActions.add(new JMenuItem("Delete from friends"));
        friendActions.add(new JMenuItem("Start conversation"));
        friendActions.add(new JMenuItem("Rename the Contact"));

        friendActions.forEach(e -> {
            e.setBackground(Color.RED.darker());
            e.setForeground(Color.WHITE);
            e.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        });

        addButtonActions(friendActions, menu);

        return friendActions;

    }

    private void addButtonActions(List<JMenuItem> list, JMenu menu) {
        list.get(2).addActionListener(e -> {
            String str = JOptionPane.showInputDialog(null,"Input new Name");

            if (!str.equals("")) {
                menu.setText(str);

                friendListUpdate();
            }

        });

        list.get(0).addActionListener(e -> {

            List<JMenu> deleted = friends.values().stream().filter(ev -> ev.getText().equals(menu.getText())).collect(Collectors.toList());
            parent.remove(deleted.get(0));
            friends.values()
                    .removeIf(ev -> ev.getText().equals(menu.getText()));

            String tempFriends = friends.keySet().stream().collect(Collectors.joining(" "));

            AppUserModel.getInstance().setFriends(tempFriends);
            friendListUpdate();
            String friendList =  friends.values().stream().map(AbstractButton::getText).collect(Collectors.joining(" "));
            UserController.getInstance().updateFriendList(AppUserModel.getInstance().getEmail(),friendList);

        });

    }

    public void friendListUpdate() {
        ReadWrite.getInstance().saveFriendConfig(this.friends);
    }

    public void addFriend() {

        List<FriendModel> list = AppUserModel.getInstance().getFriends();
        JMenu menu = new JMenu(list.get(list.size()-1).getMail());
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        parent.add(menu);
        friendListAction(menu).forEach(menu::add);
        friends.put(list.get(list.size()-1).getMail(),menu);
        friendListUpdate();



    }

    public Map<String, JMenu> getFriendList() {
        return friends;
    }

}
