package vian.mobile.gamana;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BookingDetailsDB extends SQLiteOpenHelper {
    public static final String DBNAME = "bookings";
    public static final String TABLENAME = "BookingDetails";
    public static final int VER = 1;

    public BookingDetailsDB(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLENAME + "(vehicleID integer primary key, vehicleImage integer, vehicleName text, vehicleColour text, engineCapacity double, vehiclePrice double, pickUpDate text, dropOffDate text, noOfDays integer, totalCost double)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists " + TABLENAME + "";
        db.execSQL(query);
    }
}