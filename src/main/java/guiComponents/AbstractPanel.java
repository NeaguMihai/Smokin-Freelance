package guiComponents;

import modelControllerInterfaces.FramesController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public abstract class AbstractPanel {

    private FramesController manager;
    private ButtonTarget type;

    public AbstractPanel(FramesController manager) {
        this.manager = manager;
    }

    public abstract void linkButtonAction();
    public abstract void refresh();
    public void keyEnterTrigger() {}

    public FramesController getManager() {
        return manager;
    }


    public void roundJTextField(List<JTextField> textArea) {
        textArea.forEach(e -> e.setBorder(new LineBorder(Color.BLACK,1,true)));
        textArea.forEach(e -> {
            e.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        keyEnterTrigger();
                    }

                }
            });
        });
    }

    public ButtonTarget getType() {
        return type;
    }
}
