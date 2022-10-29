package com.example.vehiclerentalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VehicleDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Vehicle.db";
    public static final String TABLE_NAME = "vehicle_table";
    //public static final String COL_0 = "ID";
    public static final String COL_1 = "VEHICLENO";
    public static final String COL_2 = "TYPE";
    public static final String COL_3 = "MODEL";
    public static final String COL_4 = "PASSENGERS";

    public VehicleDB( Context context ) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase(); //later remove - creating db
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(VEHICLENO TEXT PRIMARY KEY, TYPE TEXT, MODEL TEXT, PASSENGERS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

    }

    public boolean insertVehicle(String vehicleNo, String vehicleType, String vehicleModel, String noOfPassengers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, vehicleNo);
        contentValues.put(COL_2, vehicleType);
        contentValues.put(COL_3, vehicleModel);
        contentValues.put(COL_4, noOfPassengers);
        long result = db.insert(TABLE_NAME, null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllVehicles(){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("select * from " +TABLE_NAME, null);
        return res;
    }

    public boolean updateVehicle(String vehicleNo, String vehicleType, String vehicleModel, String noOfPassengers)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, vehicleNo);
        contentValues.put(COL_2, vehicleType);
        contentValues.put(COL_3, vehicleModel);
        contentValues.put(COL_4, noOfPassengers);
        db.update(TABLE_NAME, contentValues, "VEHICLENO =?", new String[]{vehicleNo});
        return true;
    }

    public Integer deleteData(String vehicleNo){
        SQLiteDatabase db = this.getWritableDatabase();
         return db.delete( TABLE_NAME, "VEHICLENO = ?", new String[]{vehicleNo});
    }

}
