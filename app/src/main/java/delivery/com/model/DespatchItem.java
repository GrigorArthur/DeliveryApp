package delivery.com.model;

import java.io.Serializable;

/**
 * Created by Caesar on 4/21/2017.
 */

public class DespatchItem implements Serializable {
    private String despatchId;
    private String driverName;
    private String creationDate;
    private String runId;
    private int completed;

    public DespatchItem() {
        despatchId = "";
        runId = "";
        driverName = "";
        creationDate = "";
        completed = 0;
    }

    public void setDespatchId(String value) {
        this.despatchId = value;
    }

    public String getDespatchId() {
        return despatchId;
    }

    public void setRunId(String value) {
        this.runId = value;
    }

    public String getRunId() {
        return runId;
    }

    public void setDriverName(String value) {
        this.driverName = value;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setCreationDate(String value) {
        this.creationDate = value;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCompleted(int value) {
        this.completed = value;
    }

    public int getCompleted() {
        return completed;
    }
}
