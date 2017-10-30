package com.example.raghu.healthatron;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

/**
 * Created by Raghu on 12/4/2016.
 */
public class DatabaseHelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "hospital.db";
    public static final String TABLE_DOCTOR = "doctor_table";
    public static final String Q1 = "create table " + TABLE_DOCTOR + "(ID TEXT, PW TEXT, NAME TEXT, SPL TEXT)";
    public static final String Q2 = "create table pat_table (PHN TEXT, PPW TEXT, PNAME TEXT)";
    public static final String Q3 = "create table apt_table (PNAME TEXT, PHN TEXT, UID TEXT, APTAT TEXT)";

    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Q1);
        db.execSQL(Q2);
        db.execSQL(Q3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String id, String pw, String name, String spl)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("ID", id);
        c.put("PW", pw);
        c.put("NAME", name);
        c.put("SPL", spl);
        db.insert(TABLE_DOCTOR, null, c);
        return true;
    }

    public boolean insertPat(String phone, String pw, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("PHN", phone);
        c.put("PPW", pw);
        c.put("PNAME", name);
        db.insert("pat_table", null, c);
        return true;
    }

    public boolean insertApt(String name, String phone, String uid, String dt, String tm)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("PNAME", name);
        c.put("PHN", phone);
        c.put("UID", uid);
        c.put("APTAT", dt + " " + tm);
        db.insert("apt_table", null, c);
        return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from apt_table", null);
        return res;
    }

    public Cursor getName(String spl)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_DOCTOR + " where SPL=?", new String[]{spl});
        String s = String.valueOf(res.getCount());
        return res;
    }

    public Cursor getPhone(String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from pat_table where PHN=?", new String[]{phone});
        String s = String.valueOf(res.getCount());
        return res;
    }

    public Cursor getApt(String uid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from apt_table where UID=?", new String[]{uid});
        return res;
    }

    public Cursor getAptByName(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from apt_table where PNAME=?", new String[]{name});
        return res;
    }

    public void delAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete * from " + TABLE_DOCTOR);
    }
}
