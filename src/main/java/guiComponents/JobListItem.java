package guiComponents;

import algorithms.FormatText;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class JobListItem {
    private JPanel panel1;
    private JButton moreButton;
    private JLabel money;
    private JLabel name;
    private JLabel level;
    private JPopupMenu popupMenu;

    public JobListItem(String name, int money, int level, String details) {
        this.name.setText(name);
        this.money.setText(money + "$");
        this.level.setText(level + "");
        String str = FormatText.getFormatedText(details);
        detailsButton(str);
        moreButton.addActionListener(e -> {
            popupMenu.show(moreButton, moreButton.getWidth()/2, moreButton.getHeight()/2);
        });
        panel1.setSize(550,50);
    }



    public JPanel getPanel1() {
        return panel1;
    }

    private void detailsButton(String str) {
        popupMenu = new JPopupMenu("popup");
        JTextPane pane = new JTextPane();
        pane.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        pane.setText(str);
        popupMenu.add(pane);
    }
}
