package com.hansung.android.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dy80 on 2017-11-17.
 */

public class MenuDBHelper extends SQLiteOpenHelper {

    public MenuDBHelper(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserContract.Restaurant.CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(UserContract.Restaurant.CREATE_TABLE2);
        onCreate(sqLiteDatabase);
    }

    public long insertRestaurantMenuMethod(String Menu_image, String Menu_name, String Menu_price, String Menu_report, String Menu_star) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.Restaurant.Menu_image, Menu_image);
        values.put(UserContract.Restaurant.Menu_name, Menu_name);
        values.put(UserContract.Restaurant.Menu_price, Menu_price);
        values.put(UserContract.Restaurant.Menu_report, Menu_report);
        values.put(UserContract.Restaurant.Menu_star, Menu_star);

        return db.insert(UserContract.Restaurant.TABLE_NAME,null,values);
    }

    public Cursor getAllRestaurantMenuMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(UserContract.Restaurant.TABLE_NAME,null,null,null,null,null,null);
    }

    public void deleteRestaurantMenuSQL(){
        SQLiteDatabase db = getReadableDatabase();

        db.execSQL("DELETE FROM "+ UserContract.Restaurant.TABLE_NAME);
    }

}

