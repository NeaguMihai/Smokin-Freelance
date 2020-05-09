package model;

import controller.UserController;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UserModel {

    private int id;

    private String name;

    private String email;

    private String password;

    private int buzzNumber;

    private List<FriendModel> friendlist;

    public UserModel(int id, String name, String email, String password, String friends, int buzzNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.buzzNumber = buzzNumber;

        setFriends(friends);
    }

    public UserModel(int id, String name, String email, String password,int buzzNumber, LinkedList<FriendModel> friends) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.buzzNumber = buzzNumber;
        this.friendlist = friends;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return  "id=" + id +
                ", nume='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setFriends(String friends) {
        friendlist = new LinkedList<>();
        if (friends.length()!=0) {
            String[] str = friends.split(" ");

            List<String> mails = Arrays.asList(str);

            mails.forEach(e -> friendlist.add(new FriendModel(e)));

        }


    }

    public void setFriends(List<FriendModel> friends) {
        this.friendlist = friends;
    }

    public void addFriend(UserModel user) {

        boolean stmt1 = !user.getEmail().equals(AppUserModel.getInstance().getEmail());

        boolean stmt2 = AppUserModel.getInstance().getFriends().stream().filter(e -> e.getMail().equals(user.getEmail())).count() == 0;
        if (stmt1 && stmt2) {
            friendlist.add(new FriendModel(user.getEmail()));

        }else
            JOptionPane.showMessageDialog(null,"The user is already in the friend list");

    }

    public int getBuzzNumber() {
        return buzzNumber;
    }

    public void setBuzzNumber(int buzzNumber) {
        this.buzzNumber = buzzNumber;
    }

    public String getFriendsString() {
        String str =  friendlist.stream().map(FriendModel::toString).collect(Collectors.joining(" "));
        System.out.println("Stringul din lista"+str);
        return str;
    }
    public List<FriendModel> getFriends() {
        return  friendlist;
    }


}
