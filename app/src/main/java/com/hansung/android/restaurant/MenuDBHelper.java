package com.hansung.android.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dy80 on 2017-11-17.
 */

public class MenuDBHelper extends SQLiteOpenHelper {

    public MenuDBHelper(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserContract.RestaurantMenu.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(UserContract.RestaurantMenu.CREATE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long insertRestaurantMenuMethod(String Menu_image, String Menu_name, String Menu_price, String Menu_star) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.RestaurantMenu.Menu_image, Menu_image);
        values.put(UserContract.RestaurantMenu.Menu_name, Menu_name);
        values.put(UserContract.RestaurantMenu.Menu_price, Menu_price);
        values.put(UserContract.RestaurantMenu.Menu_star, Menu_star);

        return db.insert(UserContract.RestaurantMenu.Table_name,null,values);
    }

    public Cursor getAllRestaurantMenuMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(UserContract.RestaurantMenu.Table_name,null,null,null,null,null,null);
    }

    public long deleteRestaurantMenuMethod(String _id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = UserContract.RestaurantMenu._ID +" = ?";
        String[] whereArgs ={_id};
        return db.delete(UserContract.RestaurantMenu.Table_name, whereClause, whereArgs);
    }

}

