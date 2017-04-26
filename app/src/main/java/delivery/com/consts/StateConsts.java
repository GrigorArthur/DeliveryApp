package delivery.com.consts;

/**
 * Created by Caesar on 4/21/2017.
 */

public class StateConsts {
    public static final int DESPATCH_DEFAULT = 0;
    public static final int DESPATCH_COMPLETED = 1;

    public static final int OUTLET_NOT_DELIVERED = 0;
    public static final int OUTLET_DELIVERED = 1;
    public static final int OUTLET_CANNOT_DELIVER = 2;

    public static final int STOCK_QTY_FULL = 1;
    public static final int STOCK_QTY_RESTOCK = 2;
    public static final int STOCK_QTY_NONE = 3;
    public static final int STOCK_QTY_MISSING = 4;

    public static final int REASON_CLOSED = 1;
    public static final int REASON_NO_ACCESS = 2;
    public static final int REASON_OUT_OF_HOURS_CLOSED = 3;
    public static final int REASON_REFURBISHMENT_CLOSED = 4;
    public static final int REASON_SITE_DOWN = 5;
    public static final int REASON_DISPLAY_NOT_ACCESSIBLE = 6;
    public static final int REASON_DISPLAY_MISSING = 7;
    public static final int REASON_DISPLAY_BROKEN = 8;
    public static final int REASON_DISPLAY_TEMPORARILY_PUT = 9;
    public static final int REASON_COULDNT_LOCATE = 10;
    public static final int REASON_NOT_ENOUGH_TIME = 11;
    public static final int REASON_DELIVERED = 12;

}
