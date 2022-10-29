package com.example.vehiclerentalapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FeedbackDB extends SQLiteOpenHelper {

    public static final String DBName = "Feedback.db";
    public static final String TABLE_NAME = "feedback";
    public static final String COL_1 = "FID";
    public static final String COL_2 = "VRATING";
    public static final String COL_3 = "DRATING";
    public static final String COL_4 = "DESCRIPTION";

    public FeedbackDB( Context context) {
        super(context, DBName,null,1);
        //SQLiteDatabase db = this.getWritableDatabase(); //- creating feedback db
//
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //primary key?
//        MyDB.execSQL("create Table feedback(fid TEXT PRIMARY KEY AUTOINCREMENT NOT NULL, vehicleRating REAL, driverRating REAL, feedbackDesc TEXT )");
//        MyDB.execSQL("create Table feedback(fid TEXT PRIMARY KEY, vehicleRating REAL, driverRating REAL, feedbackDesc TEXT )");
        MyDB.execSQL("create Table " +TABLE_NAME+"(FID INTEGER PRIMARY KEY AUTOINCREMENT, VRATING REAL, DRATING REAL, DESCRIPTION TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(MyDB);
    }

    public Boolean insertFeedback ( String vehicleRating, String driverRating, String feedbackDesc ){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, vehicleRating );
        contentValues.put(COL_3, driverRating );
        contentValues.put(COL_4, feedbackDesc);
        long result = MyDB.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
}