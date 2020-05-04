package guiComponents;

import controller.FramesController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public abstract class AbstractPanel {

    private FramesController manager;
    private ButtonTarget type;

    public AbstractPanel(FramesController manager) {
        this.manager = manager;
    }

    public abstract void linkButtonAction();

    public FramesController getManager() {
        return manager;
    }


    public void roundJTextField(List<JTextField> textArea) {
        textArea.forEach(e -> e.setBorder(new LineBorder(Color.BLACK,1,true)));
    }

    public ButtonTarget getType() {
        return type;
    }
}
