package delivery.com.model;

/**
 * Created by Caesar on 4/21/2017.
 */

public class OutletItem {
    private String outletId;
    private String outlet;
    private int serviceType;
    private int delivered;
    private String deliveredTime;
    private int tiers;
    private int reason;
    private int stock;

    public OutletItem() {
        this.outletId = "";
        this.outlet = "";
        this.serviceType = 0;
        this.delivered = 0;
        this.deliveredTime = "";
        this.tiers = 0;
        this.reason = 0;
        this.stock = 0;
    }

    public void setOutletId(String value) {
        this.outletId = value;
    }

    public String getOutletId() {
        return outletId;
    }
    public void setOutlet(String value) {
        this.outlet = value;
    }

    public String getOutlet() {
        return outlet;
    }

    public void setServiceType(int value) {
        this.serviceType = value;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setDelivered(int value) {
        this.delivered = value;
    }

    public int getDelivered() {
        return delivered;
    }

    public void setDeliveredTime(String value) {
        this.deliveredTime = value;
    }

    public String getDeliveredTime() {
        return deliveredTime;
    }

    public void setTiers(int value) {
        this.tiers = value;
    }

    public int getTiers() {
        return tiers;
    }

    public void setReason(int value) {
        this.reason = value;
    }

    public int getReason() {
        return this.reason;
    }

    public void setStock(int value) {
        this.stock = value;
    }

    public int getStock() {
        return stock;
    }
}
