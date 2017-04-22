package delivery.com.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import delivery.com.consts.DBConsts;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME_PREFIX = "GLIDE_DB";
    private static final int DB_VERSION = 2;

    protected static String BRANCH_TABLE_CREATE_SQL =
            "CREATE TABLE IF NOT EXISTS " + DBConsts.TABLE_NAME_DESPATCH + " (" +
                    DBConsts.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBConsts.FIELD_DESPATCH_ID + " TEXT," +
                    DBConsts.FIELD_RUN + " TEXT," +
                    DBConsts.FIELD_DRIVER + " TEXT," +
                    DBConsts.FIELD_DATE + " TEXT," +
                    DBConsts.FIELD_COMPLETED + " INTEGER);";

    public DBHelper(Context context) {
        super(context, DB_NAME_PREFIX, null, DB_VERSION);
        this.getWritableDatabase().close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(BRANCH_TABLE_CREATE_SQL);
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
