package delivery.com.model;

/**
 * Created by Caesar on 4/21/2017.
 */

public class StockItem {
    private String stockId;
    private String stock;
    private int tier;
    private int slot;
    private int qty;

    public StockItem() {
        stockId = "";
        stock = "";
        tier = 0;
        slot = 0;
        qty = 0;
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
}
