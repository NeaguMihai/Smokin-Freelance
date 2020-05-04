package controller;

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

    public ButtonTarget getType() {
        return type;
    }
}
