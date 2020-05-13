package model;

import java.io.Serializable;
import java.util.List;

public class HistoryHolder implements Serializable {

    private List<String> jobHistory;

    public HistoryHolder(List<String> jobHistory) {
        this.jobHistory = jobHistory;
    }

    public List<String> getJobHistory() {
        return jobHistory;
    }
}
