package com.group3.smarthome;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String Database_name="SmartHomeManagementSystem.db";
    static final int Database_Version=1;
    SQLiteDatabase db;
    public int id_this;
    Cursor cursor;

    // Users table
    //static String TABLE_NAME="Users";
    static String Id="_id";
    static String account="account";
    static String password="password";
    static String usertype="usertype";
    static String telephone="telephone";
    static String email="email";

    // Sensor Table
    static String roonname="roomname";
    static String smoke="smoke";
    static String CO="CO";
    static String date="date";
    static String state="state";
    static String temperature="temperature";
    static String humidity="humidity";

    // Room table
    static String smokestate="smokesatate";
    static String windowsstate="windowsstate";
    DbHelper(Context ctx){
        super(ctx,Database_name,null,Database_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        String sqluser="CREATE TABLE "+ "Users" +"("
                + Id +" INTEGER primary key autoincrement, "
                + account +" text not null, "
                + password+" text not null, "
                + usertype +" text not null, "
                + telephone +" text not null, "
                + email +" text not null "+ ");";
        // Smoke sensor table
        String sqlsmoke="CREATE TABLE "+ "SmokeSensor" +"("
                + Id +" INTEGER primary key autoincrement,"
                + roonname +" text not null,"
                + smoke +" text not null,"
                + CO +" text not null,"
                + date +" text not null " + ");";
        // doors and windows sensor table
        String sqlwindows="CREATE TABLE "+ "WindowsSensor" +"("
                + Id +" INTEGER primary key autoincrement,"
                + roonname +" text not null,"
                + smoke +" text not null,"
                + state +" text not null,"
                + date +" text not null " + ");";
        // temp and hum sensor table
        String sqltemhum="CREATE TABLE "+ "TemHumSensor" +"("
                + Id +" INTEGER primary key autoincrement,"
                + roonname +" text not null,"
                + temperature +" text not null,"
                + humidity +"text not null,"
                + date +" text not null " + ");";
        // kitchen
        String sqlkitchen="CREATE TABLE "+ " KitchenInfo " +"("
                + Id +" INTEGER primary key autoincrement,"
                + smokestate +" text not null,"
                + windowsstate +" text not null " + ");";
        // bathroom
        String sqlbathroom="CREATE TABLE "+ " BathroomInfo " +"("
                + Id +" INTEGER primary key autoincrement,"
                + smokestate +" text not null,"
                + windowsstate +" text not null " + ");";
        // bedroom
        String sqlbedroom="CREATE TABLE "+ " BedroomInfo " +"("
                + Id +" INTEGER primary key autoincrement,"
                + smokestate +" text not null,"
                + windowsstate +" text not null,"
                + temperature +" text not null, "
                + date +" text not null "+ ");";
        database.execSQL(sqluser);
        database.execSQL(sqlsmoke);
        database.execSQL(sqlwindows);
        database.execSQL(sqltemhum);
        database.execSQL(sqlkitchen);
        database.execSQL(sqlbathroom);
        database.execSQL(sqlbedroom);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

