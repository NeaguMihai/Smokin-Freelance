package algorithms;

public class FormatText {

    //Metoda care se ocupa cu formatarea txtului pentru afisarea JTextArea
    //Am folosit un stringBuilder in cazul in care sunt foarte multe stringuri
    public static String getFormatedText(String string) {

        StringBuilder sb = new StringBuilder("");

        String [] tokes = string.split(" ");
        int j = 0;
        for (int i =0; i < tokes.length; i++) {
            if (sb.length() > j + 40) {
                sb.append("\n");
                j = sb.length();
            }

            sb.append(tokes[i]).append(" ");
        }

        return sb.toString();

    }
}
