package modelControllerInterfaces;

import guiComponents.ButtonTarget;

public interface FramesController {
    public void switchFrameTo(ButtonTarget target);
    public void closeApp();
    public void refreshPage();
}
