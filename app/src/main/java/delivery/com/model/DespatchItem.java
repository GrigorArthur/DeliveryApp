package delivery.com.model;

/**
 * Created by Caesar on 4/21/2017.
 */

public class DespatchItem {
    private String despatchId;
    private String driverName;
    private String creationDate;
    private String runId;

    public DespatchItem() {
        despatchId = "";
        runId = "";
        driverName = "";
        creationDate = "";
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
}
