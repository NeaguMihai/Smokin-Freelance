package model;

import controller.AdminController;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


public class HistoryHolder implements Serializable {


    public static void addLog(String string) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        AdminController.getInstance().addLog(format.format(now) + " " + string);
    }
}
