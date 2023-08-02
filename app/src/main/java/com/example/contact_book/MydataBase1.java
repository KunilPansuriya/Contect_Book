package com.example.contact_book;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MydataBase1 extends SQLiteOpenHelper {

    public MydataBase1(@Nullable Context context) {
        super(context, "ContactBook1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table ContactTable(ID Integer primary key autoincrement,NAME text,EMAIL text,NUMBER text,IMGURI text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addData(String name, String email, String number, String resultUri) {
        String query = "insert into ContactTable(NAME,EMAIL,NUMBER,IMGURI) values('"+name+"','"+email+"','"+number+"','"+resultUri+"')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

    }



    public Cursor data() {
        String query = "select * from ContactTable";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public void updateData(int id, String name, String number, String email) {
        String query="update ContactTable set NAME='"+name+"',EMAIL='"+email+"',NUMBER='"+number+"' where ID="+id+"";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }

//    public void deleteData(int contactModal) {
//        String query="delete from ContactTable where NAME="+getDatabaseName()+"";
//        SQLiteDatabase db=getWritableDatabase();
//        db.execSQL(query);
//    }

    public void delete(int id) {
        String query="delete from ContactTable where ID="+id+"";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);
    }
}
//    public void deleteData(Integer id)
//    {
//        String query="delete from Emp where ID="+id+"";
//        SQLiteDatabase db=getWritableDatabase();
//        db.execSQL(query);
//    }
//
//    public Cursor showdata() {
//        String query = "select * from ContactTable";
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery(query,null);
//        return cursor;
//    }
//
//    public Cursor showdata1() {
//        String query = "select * from ContactTable";
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery(query,null);
//        return cursor;
//    }