package algorithms;

import com.mysql.cj.util.EscapeTokenizer;

import java.util.Scanner;

public class IsNumber {

    public static boolean isInteger(String str) {
        if (str.split(" ").length !=1) return false;
        else {
            Scanner sc = new Scanner(str);
            boolean res = sc.hasNextInt(10);
            sc.close();
            return res;
        }

    }

    public static boolean isLong(String str) {
        if (str.split(" ").length !=1) return false;
        else {
            Scanner sc = new Scanner(str);
            boolean res = sc.hasNextLong(10);
            sc.close();
            return res;
        }

    }


}
