package com.hansung.android.restaurant;


import android.provider.BaseColumns;

public class UserMenu {
    public static final String DB_NAME="RestaurantMenu.db";
    public static final int DATEBASE_VERSION = 2;

    private UserMenu(){}

    public static class RestaurantMenu implements BaseColumns {
        public static final String TABLE_NAME = "Menu";
        public static final String Menu_image = "Menuimage";
        public static final String Menu_name = "Menuname";
        public static final String Menu_price = "Menuprice";
        public static final String Menu_report = "Menureport";
        public static final String Menu_star = "Menustar";

        public static final String CREATE_TABLE2 = "create table "+ TABLE_NAME +"("+
                _ID + " INTEGER PRIMARY KEY"+","+
                Menu_image+" TEXT" + "," +
                Menu_name+" TEXT" + "," +
                Menu_price+" TEXT" + "," +
                Menu_report+" TEXT" + ","+
                Menu_star+" TEXT" +  ")";
    }

}