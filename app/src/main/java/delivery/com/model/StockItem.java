package delivery.com.model;

import java.io.Serializable;

/**
 * Created by Caesar on 4/21/2017.
 */

public class StockItem implements Serializable {
    private String despatchID;
    private String outletID;
    private String stockId;
    private String stock;
    private int tier;
    private int slot;
    private int qty;
    private String status;
    private String remove;
    private String removeID;
    private String titleID;
    private String size;

    public StockItem() {
        despatchID = "";
        outletID = "";
        stockId = "";
        stock = "";
        tier = 0;
        slot = 0;
        qty = 0;
        status = "";
        remove = "";
        removeID = "";
        titleID = "";
        size = "";
    }

    public void setDespatchID(String value) {
        this.despatchID = value;
    }

    public String getDespatchID() {
        return despatchID;
    }

    public void setOutletID(String value) {
        this.outletID = value;
    }

    public String getOutletID() {
        return outletID;
    }

    public void setStockId(String value) {
        this.stockId = value;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStock(String value) {
        this.stock = value;
    }

    public String getStock() {
        return stock;
    }

    public void setTier(int value) {
        this.tier = value;
    }

    public int getTier() {
        return tier;
    }

    public void setSlot(int value) {
        this.slot = value;
    }

    public int getSlot() {
        return slot;
    }

    public void setQty(int value) {
        this.qty = value;
    }

    public int getQty() {
        return qty;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getStatus() {
        return status;
    }

    public void setRemove(String value) {
        this.remove = value;
    }

    public String getRemove() {
        return remove;
    }

    public void setRemoveID(String value) {
        this.removeID = value;
    }

    public String getRemoveID() {
        return removeID;
    }

    public void setTitleID(String value) {
        this.titleID = value;
    }

    public String getTitleID() {
        return titleID;
    }

    public void setSize(String value) {
        this.size = value;
    }

    public String getSize() {
        return size;
    }
}
