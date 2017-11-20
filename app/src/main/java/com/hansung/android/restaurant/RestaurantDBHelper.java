package com.hansung.android.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class RestaurantDBHelper extends SQLiteOpenHelper {

    public RestaurantDBHelper(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserContract.Restaurant.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(UserContract.Restaurant.CREATE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long insertRestaurantMethod(String Restaurant_image, String Restaurant_name, String Restaurant_address, String Restaurant_phone,
                                       String Restaurant_open) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserContract.Restaurant.Restaurant_image, Restaurant_image);
        values.put(UserContract.Restaurant.Restaurant_name, Restaurant_name);
        values.put(UserContract.Restaurant.Restaurant_address, Restaurant_address);
        values.put(UserContract.Restaurant.Restaurant_phone, Restaurant_phone);
        values.put(UserContract.Restaurant.Restaurant_open, Restaurant_open);

        return db.insert(UserContract.Restaurant.Table_name,null,values);
    }

    public Cursor getAllRestaurantMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(UserContract.Restaurant.Table_name,null,null,null,null,null,null);
    }


    public void deleteRestaurantSQL(){
        SQLiteDatabase db = getReadableDatabase();

         db.execSQL("DELETE FROM "+ UserContract.Restaurant.Table_name);
    }

}
