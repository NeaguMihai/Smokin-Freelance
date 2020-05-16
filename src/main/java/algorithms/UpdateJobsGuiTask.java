package algorithms;

import controller.JobController;
import gui.AppBody;

public class UpdateJobsGuiTask implements Runnable {

    private AppBody appBody;


    public UpdateJobsGuiTask(AppBody appBody) {
        this.appBody = appBody;
    }

    @Override
    public void run() {
        JobController.getInstance().getAcceptedJobs();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appBody.createJobList();


        }

    }

