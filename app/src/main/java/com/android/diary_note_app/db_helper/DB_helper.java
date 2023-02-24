package com.android.diary_note_app.db_helper;

import android.content.Context;
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

    public void getList(){
        String str =
                "";
        callDB(str);
    }

    private void callDB(String str){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(str);
        db.close();
    }



}
