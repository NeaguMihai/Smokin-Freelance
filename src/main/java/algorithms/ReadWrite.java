package algorithms;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ReadWrite {

    private static Path path = Paths.get("./src/main/resources/saveData/help.txt");

//metoda care incarca in meniul de help textul
    public static String getHelpText() {

        try {
            List<String> list = Files.readAllLines(path);
            return String.join("\n", list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

//        int a = sc.nextInt();
int i;
        for (i = 100; i >= 1; i-- ) {

            if (i%9 == 0) {
                System.out.println("gasit");
            }
        }
}

}

