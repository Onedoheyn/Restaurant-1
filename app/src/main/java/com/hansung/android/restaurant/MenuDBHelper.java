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
        super(context, UserMenu.DB_NAME, null, UserMenu.DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserMenu.RestaurantMenu.CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(UserMenu.RestaurantMenu.CREATE_TABLE2);
        onCreate(sqLiteDatabase);
    }

    public long insertRestaurantMenuMethod(String Menu_image, String Menu_name, String Menu_price, String Menu_report, String Menu_star) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserMenu.RestaurantMenu.Menu_image, Menu_image);
        values.put(UserMenu.RestaurantMenu.Menu_name, Menu_name);
        values.put(UserMenu.RestaurantMenu.Menu_price, Menu_price);
        values.put(UserMenu.RestaurantMenu.Menu_report, Menu_report);
        values.put(UserMenu.RestaurantMenu.Menu_star, Menu_star);

        return db.insert(UserMenu.RestaurantMenu.TABLE_NAME,null,values);
    }

    public Cursor getAllRestaurantMenuMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(UserMenu.RestaurantMenu.TABLE_NAME,null,null,null,null,null,null);
    }

    public void deleteRestaurantMenuSQL(){
        SQLiteDatabase db = getReadableDatabase();

        db.execSQL("DELETE FROM "+ UserMenu.RestaurantMenu.TABLE_NAME);
    }

}

