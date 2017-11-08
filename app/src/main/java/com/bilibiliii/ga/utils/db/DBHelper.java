package com.bilibiliii.ga.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author No.47 create at 2017/10/27.
 */
class DBHelper extends SQLiteOpenHelper {
    // database name
    static final String DATABASE_NAME = "GA_USER.db";

    // table name
    static final String TABLE_NAME = "UserAvatar";

    // key of avatar
    static final String AVATAR = "avatar";

    // key of id
    static final String ID = "id";

    // default save into index 0
    static final int DEFAULT_ID = 0;

    // default version
    private static final int version = 1;

    private static DBHelper dbHelper;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory f, int v) {
        super(context, name, f, v);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    public static DBHelper getInstance(Context context) {

        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTb = "CREATE TABLE if not exists " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AVATAR + " BLOB)";
        db.execSQL(createTb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}