package algorithms;

import controller.JobController;
import gui.AppBody;
import model.AppUserModel;

public class UpdateJobsGuiTask implements Runnable {

    private AppBody appBody;


    public UpdateJobsGuiTask(AppBody appBody) {
        this.appBody = appBody;
    }
//un task ce actualizeaza lista de joburi acceptate
    @Override
    public void run() {


        try {
            //Am pus sleep pentru a simula o tranzitie
            //nu am vrut ca schimbarea sa fie prea brusca
            Thread.sleep(100);
            JobController.getInstance().getAcceptedJobs();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appBody.createJobList();


        }

    }

