package AccountConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.AppUserModel;
import model.HistoryHolder;

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
        friendConfigPath = Paths.get("src/main/resources/saveData/ asd.cfg");

    }


    private static final class SingletonHolder {
        private static final ReadWrite INSTANCE = new ReadWrite();
    }

    public void saveObject() {
        HistoryHolder hh = new HistoryHolder(Arrays.asList("das","asdas"));

        try {
            FileOutputStream fos = new FileOutputStream(friendConfigPath.toString());
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(hh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HistoryHolder getObject() {
        HistoryHolder hh;

        try {
            FileInputStream fis = new FileInputStream(friendConfigPath.toString());
            ObjectInputStream os = new ObjectInputStream(fis);
            hh = (HistoryHolder) os.readObject();
            return hh;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HistoryHolder(Collections.singletonList(""));
        }

    }


    public static ReadWrite getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Path getFriendConfigPath() {
        return friendConfigPath;
    }
}
