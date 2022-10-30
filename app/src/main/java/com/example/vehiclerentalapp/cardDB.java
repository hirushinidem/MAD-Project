package com.example.vehiclerentalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class cardDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "card.db";
    public static final String TABLE_NAME = "card_table";
    public static final String COL_1 = "CARD_ID";
    public static final String COL_2 = "CARD_NUMBER";
    public static final String COL_3 = "EXPIRE_DATE";
    public static final String COL_4 = "CVC";



    public cardDB(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(CARD_ID INTEGER PRIMARY KEY AUTOINCREMENT , CARD_NUMBER INTEGER , EXPIRE_DATE DATE , CVC INTEGER)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String cardNumber , String expDate , String cvc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 , cardNumber);
        contentValues.put(COL_3 , expDate);
        contentValues.put(COL_4 , cvc);
        long result = db.insert(TABLE_NAME , null , contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME , null );
        return res ;
    }

    public boolean updateData(String cid , String cardNumber , String expDate , String cvc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1 , cid);
        contentValues.put(COL_2 , cardNumber);
        contentValues.put(COL_3 , expDate);
        contentValues.put(COL_4 , cvc);
        db.update(TABLE_NAME , contentValues , "CARD_ID = ?" , new String[] {cid});
        return true ;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME , "CARD_ID = ?" , new String[] {id});
    }
}
