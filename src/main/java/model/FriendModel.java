package model;

import java.io.Serializable;

public class FriendModel implements Serializable {
    private String mail;

    public FriendModel( String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return
                  mail ;
    }
}
