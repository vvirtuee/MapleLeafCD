package com.example.mapleleafcd.database;

public class DBConst {

    public static final String TABLE_ITEM ="item";
    public static final String ITEM_COLUMN_ID = "id";
    public static final String ITEM_COLUMN_NAME = "name";
    public static final String ITEM_COLUMN_YEAR = "year";
    public static final String ITEM_COLUMN_CONDITION = "coin_condition";
    public static final String ITEM_COLUMN_LOCATION = "location";

    //location table
    public static final String TABLE_LOCATION = "location";
    public static final String LOCATION_COLUMN_ID = "id";
    public static final String LOCATION_COLUMN_NAME = "name";
    public static final String LOCATION_COLUMN_LONGITUDE = "longitude";
    public static final String LOCATION_COLUMN_LATITUDE = "latitude";

    //COIN CONDITION TABLE
    public static final String TABLE_CONDITION = "coin_condition";
    public static final String CONDITION_COLUMN_ID = "id";
    public static final String CONDITION_COLUMN_NAME = "name";

    //COIN NAME TABLE
    public static final String TABLE_COIN = "coin";
    public static final String COIN_COLUMN_ID = "id";
    public static final String COIN_COLUMN_NAME = "name";

    //TABLE CREATE STATEMENTS
    public static final String CREATE_TABLE_LOCATION = "CREATE TABLE " + TABLE_LOCATION + " (" + LOCATION_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " + LOCATION_COLUMN_NAME + " VARCHAR(50), " +
            LOCATION_COLUMN_LONGITUDE + " VARCHAR(15), " + LOCATION_COLUMN_LATITUDE + " VARCHAR(15), " + "PRIMARY_KEY(" + LOCATION_COLUMN_ID + ")" + ");";
    public static final String CREATE_TABLE_COIN = "CREATE TABLE " + TABLE_COIN + " (" + COIN_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " + COIN_COLUMN_NAME + " PRIMARY KEY(" +
            COIN_COLUMN_ID + ")" + ");";
    public static final String CREATE_TABLE_COIN_CONDITION = "CREATE TABLE " + TABLE_CONDITION + " (" + CONDITION_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " + CONDITION_COLUMN_NAME + " VARCHAR(50, " +
            " PRIMARY KEY(" + CONDITION_COLUMN_ID + ") " + ");";
    public static final String CREATE_TABLE_ITEMS = "CREATE TABLE " + TABLE_ITEM  + " (" + ITEM_COLUMN_ID + " int NOT NULL AUTO_INCREMENT PRIMARY KEY, " + ITEM_COLUMN_NAME +
            " int NOT NULL, " + ITEM_COLUMN_YEAR + " int(4) NOT NULL, " + ITEM_COLUMN_CONDITION + " int NOT NULL, " + ITEM_COLUMN_LOCATION + " int NOT NULL, " +
            "FOREIGN KEY (" + ITEM_COLUMN_LOCATION + ")" + " REFERENCES " + TABLE_LOCATION + "(" + LOCATION_COLUMN_ID + "), " +
            "FOREIGN KEY (" + ITEM_COLUMN_CONDITION + ")" + " REFERENCES " + TABLE_LOCATION + "(" + LOCATION_COLUMN_ID + "), " +
            "FOREIGN KEY (" + ITEM_COLUMN_NAME + ")" + " REFERENCES " + TABLE_LOCATION + "(" + LOCATION_COLUMN_ID + ")" + ");";
}
