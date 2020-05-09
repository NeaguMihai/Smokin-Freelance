package AccountConfig;

import model.AppUserModel;
import model.FriendList;
import model.FriendModel;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ReadWrite {

    private Path friendConfigPath ;

    private ReadWrite() {
        friendConfigPath = Paths.get("src/main/resources/saveData/ "+ AppUserModel.getInstance().getEmail() +".cfg");

    }

    public static class ListHolder {
        List<String> mails;
        List<String> customNames;

        public List<String> getMails() {
            return mails;
        }

        public void setMails(List<String> mails) {
            this.mails = mails;
        }

        public List<String> getCustomNames() {
            return customNames;
        }

        public void setCustomNames(List<String> customNames) {
            this.customNames = customNames;
        }
    }


    private static final class SingletonHolder {
        private static final ReadWrite INSTANCE = new ReadWrite();
    }


    public void saveFriendConfig(Map<String, JMenu> map) {

            String mails = String.join(" ", map.keySet());
            List<String> customNames = map.values().stream().map(AbstractButton::getText).collect(Collectors.toList());
            List<String> tempList = customNames.stream().map(e -> {
                String [] tokens = e.split(" ");
                return String.join("_", tokens);
            }).collect(Collectors.toList());
            String customNamesString = String.join(" ",tempList);

            String combined = mails + "\n" + customNamesString;

                try {
                   Files.writeString(friendConfigPath,combined,StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    public ListHolder restoreFriendConfig() {

        try {
            if (!Files.exists(friendConfigPath)) {
                Files.createFile(friendConfigPath);
            }
           List<String> str =  Files.readAllLines(friendConfigPath);
            System.out.println(str.toString());
           if (str.isEmpty()||str.get(0).equals("")) {

               return new ListHolder();
           }

           ListHolder holder = new ListHolder();
           List<String> str1 =Arrays.asList(str.get(0).split(" ")) ;
           Collections.reverse(str1);

           holder.setMails(str1);

           List<String> str2 = Arrays.asList(str.get(1).split(" "));
           str2 = str2.stream().map(e -> {
               String [] tokens = e.split("_");
               return String.join(" ", tokens);
           }).collect(Collectors.toList());

           Collections.reverse(str2);
           holder.setCustomNames(str2);

           return holder;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public static ReadWrite getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Path getFriendConfigPath() {
        return friendConfigPath;
    }
}
