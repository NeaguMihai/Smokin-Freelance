package guiComponents;

import controller.JobController;
import model.JobModel;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class JobSearch extends JFrame{
    private JPanel panel1;
    private JPanel container;
    private List<JobListItem> listItems;

    public JobSearch(int x, int y) {
        setContentPane(panel1);
        setLocation(x + 200,y);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        setSize(570, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listItems = new LinkedList<>();
        createList();
        setVisible(true);
    }

    private void createList() {
        List<JobModel> jobModels = JobController.getInstance().getJobList();
        jobModels.forEach(e -> {
            listItems.add(new JobListItem(e.getName(),e.getMoney(),e.getLevel(),e.getDetails()));
        });

        listItems.forEach(e -> container.add(e.getPanel1()));

    }
}
