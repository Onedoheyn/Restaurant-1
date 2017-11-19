package com.hansung.android.restaurant;

import android.provider.BaseColumns;

/**
 * Created by B10716 on 2017-11-16.
 */

public class UserContract {
    public static final String DB_NAME="Restaurant.db";
    public static final int DATEBASE_VERSION = 1;

    private UserContract(){}

    public static class Restaurant implements BaseColumns {
        public static final String Table_name = "Restaurant";
        public static final String Restaurant_image = "Restaurantimage";
        public static final String Restaurant_name = "Restaurantname";
        public static final String Restaurant_address = "Restaurantaddress";
        public static final String Restaurant_phone = "Restaurantphone";
        public static final String Restaurant_open = "Restaurantopen";

        public static final String CREATE_TABLE = "create table "+ Table_name +"("+_ID + " INTEGER PRIMARY KEY"+","+
                Restaurant_image+" TEXT" + "," +Restaurant_name+" TEXT" + "," +Restaurant_address+" TEXT" + "," +
                Restaurant_phone+" TEXT" + "," +Restaurant_open+" TEXT" + ")";
    }

    public static class RestaurantMenu implements BaseColumns {
        public static final String Table_name = "menu";
        public static final String Menu_image = "Menuimage";
        public static final String Menu_name = "Menuname";
        public static final String Menu_price = "Menuprice";
        public static final String Menu_star = "Menustar";

        public static final String CREATE_TABLE = "create table "+ Table_name +"("+_ID + " INTEGER PRIMARY KEY"+","+
                Menu_image+" TEXT" + "," +Menu_name+" TEXT" + "," +Menu_price+" TEXT" + "," +
                Menu_star+" TEXT" +  ")";
    }

}
