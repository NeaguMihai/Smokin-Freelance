package guiComponents;

import controller.JobController;
import gui.AppBody;
import model.JobModel;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class JobSearch extends JFrame{
    private JPanel panel1;
    private JPanel container;
    private List<JobListItem> listItems;

    public JobSearch(int x, int y, AppBody appBody) {
        setContentPane(panel1);
        setLocation(x + 200,y);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        setSize(670, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listItems = new LinkedList<>();
        createList(appBody);
        setVisible(true);
    }

    private void createList(AppBody appBody) {
        List<JobModel> jobModels = JobController.getInstance().getJobList();
        jobModels.forEach(e -> {
            listItems.add(new JobListItem(appBody, e.getId(), e.getName(),e.getMoney(),e.getLevel(),e.getDetails()));
        });

        listItems.forEach(e -> container.add(e.getPanel1()));

    }
}
