package com.example.uogcafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {

        super(context, "signin.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(ID text primary key, phone text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }
    public Boolean insertData(String ID, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", ID);
        contentValues.put("phone", phone);
        long result = db.insert("users",null,contentValues);
        return result != -1;
    }
    public boolean checkphone(String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where phone=?", new String[]{phone});
        return cursor.getCount() > 0;
    }
    public Cursor getdata()
    {
        SQLiteDatabase informatics = this.getWritableDatabase();
        return informatics.rawQuery("Select * from users", null);
    }

}









































































// programmed by yishak Terfe.....................