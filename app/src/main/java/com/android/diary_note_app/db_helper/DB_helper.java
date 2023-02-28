package com.android.diary_note_app.db_helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_helper extends SQLiteOpenHelper {
    public DB_helper(@Nullable Context context) {
        super(context, "DiaryDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        setDiaryTB(db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private void setDiaryTB(SQLiteDatabase db){
        String str =
                "CREATE TABLE DiaryDB (" +
                        "numID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "date TEXT, " +
                        "emoji TEXT, " +
                        "title TEXT, " +
                        "content TEXT, " +
                        "attachment TEXT);";
        db.execSQL(str);
    }

    public void insert(String date, String title, String emoji, String content, String attachment){
        String str =
                "INSERT INTO DiaryDB VALUES(null, '"+date+"', '"+emoji+"','"+title+"', '"+content+"', '"+attachment+"')";
        callDB(str);
    }

    public void update(){
        String str =
                "";
        callDB(str);
    }

    public void delete(){
        String str =
                "";
        callDB(str);
    }

    public void tbClear(){
        String str =
                "";
        callDB(str);
    }

    public void modify(){
        String str =
                "";
        callDB(str);
    }

    public Cursor getList(){

        SQLiteDatabase db = getReadableDatabase();
        String str =
                "Select numID, date, emoji, title, content, attachment From DiaryDB";
        Cursor cursor = db.rawQuery(str,null);
        cursor.moveToFirst();

        return cursor;
    }

    private void callDB(String str){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(str);
        db.close();
    }

    public Cursor getData(String table, String id){
        SQLiteDatabase db = getReadableDatabase();
        String str =
                "Select numID, date, emoji, title, content, attachment From " + table + " where numID = " + id ;
        Cursor cursor = db.rawQuery(str,null);
        cursor.moveToFirst();

        return cursor;
    }



}
