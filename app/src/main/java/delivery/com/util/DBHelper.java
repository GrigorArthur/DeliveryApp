package delivery.com.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import delivery.com.consts.DBConsts;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME_PREFIX = "GLIDE_DB";
    private static final int DB_VERSION = 2;

    protected static String DESPATCH_TABLE_CREATE_SQL =
            "CREATE TABLE IF NOT EXISTS " + DBConsts.TABLE_NAME_DESPATCH + " (" +
                    DBConsts.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBConsts.FIELD_DESPATCH_ID + " TEXT," +
                    DBConsts.FIELD_RUN + " TEXT," +
                    DBConsts.FIELD_DRIVER + " TEXT," +
                    DBConsts.FIELD_DATE + " TEXT," +
                    DBConsts.FIELD_COMPLETED + " INTEGER);";

    protected static String OUTLET_TABLE_CREATE_SQL =
            "CREATE TABLE IF NOT EXISTS " + DBConsts.TABLE_NAME_OUTLET + " (" +
                    DBConsts.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBConsts.FIELD_DESPATCH_ID + " TEXT," +
                    DBConsts.FIELD_OUTLET_ID + " TEXT," +
                    DBConsts.FIELD_OUTLET + " TEXT," +
                    DBConsts.FIELD_ADDRESS + " TEXT," +
                    DBConsts.FIELD_SERVICE + " TEXT," +
                    DBConsts.FIELD_DELIVERED + " TEXT," +
                    DBConsts.FIELD_DELIVER_TIME + " TEXT," +
                    DBConsts.FIELD_TIERS + " INTEGER," +
                    DBConsts.FIELD_REASON + " INTEGER," +
                    DBConsts.FIELD_COMPLETED + " INTEGER);";

    protected static String STOCK_TABLE_CREATE_SQL =
            "CREATE TABLE IF NOT EXISTS " + DBConsts.TABLE_NAME_OUTLET + " (" +
                    DBConsts.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBConsts.FIELD_DESPATCH_ID + " TEXT," +
                    DBConsts.FIELD_OUTLET_ID + " TEXT," +
                    DBConsts.FIELD_STOCK_ID + " TEXT," +
                    DBConsts.FIELD_STOCK + " TEXT," +
                    DBConsts.FIELD_TIER + " INTEGER," +
                    DBConsts.FIELD_SLOT + " INTEGER," +
                    DBConsts.FIELD_REASON + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME_PREFIX, null, DB_VERSION);
        this.getWritableDatabase().close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(DESPATCH_TABLE_CREATE_SQL);
            db.execSQL(OUTLET_TABLE_CREATE_SQL);
            db.execSQL(STOCK_TABLE_CREATE_SQL);
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            onCreate(db);
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }
}
