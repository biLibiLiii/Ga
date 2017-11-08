package com.bilibiliii.ga.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * @author No.47 create at 2017/10/27.
 */
public class DBUtils {
    private DBHelper dbHelper;
    private Context context;

    public DBUtils(Context context) {
        this.context = context;
        this.dbHelper = DBHelper.getInstance(context);
    }

    /**
     * insert bitmap to db
     */
    public void putBitmapIntoDB(Bitmap bitmap) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.AVATAR, bitmapToByte(bitmap));
        if (isDBEmpty(db)) {
            db.insert(DBHelper.TABLE_NAME, null, cv);
        } else {
            int i = db.update(DBHelper.TABLE_NAME, cv,
                    DBHelper.ID + "=?",
                    new String[]{String.valueOf(1)});
        }
        db.close();
    }

    private boolean isDBEmpty(SQLiteDatabase db) {
        Cursor cursor = db.query(DBHelper.TABLE_NAME, new String[]{DBHelper.ID, DBHelper.AVATAR},
                null, null, null, null, null);
        return cursor.getCount() <= 0;
    }

    /**
     * get bitmap from database
     * if the cursor is null, throw Exception & return a bitmap from res
     *
     * @return Bitmap
     */
    public Bitmap getBitmapFromDB(int hintIcon) {
        Bitmap bmpOut = null;
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(DBHelper.TABLE_NAME,
                    new String[]{DBHelper.ID, DBHelper.AVATAR},
                    null, null, null, null, null);
            cursor.moveToPosition(DBHelper.DEFAULT_ID);
            byte[] in = cursor.getBlob(cursor.getColumnIndex(DBHelper.AVATAR));
            bmpOut = BitmapFactory.decodeByteArray(in, 0, in.length);
            db.close();
        } catch (CursorIndexOutOfBoundsException e) {
            bmpOut = BitmapFactory.decodeResource(context.getResources(), hintIcon);
            e.printStackTrace();
        }
        return bmpOut;
    }

    private byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
