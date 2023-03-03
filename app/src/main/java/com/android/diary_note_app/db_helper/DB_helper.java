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
                        "year INTEGER," +
                        "month INTEGER," +
                        "day INTEGER," +
                        "emoji TEXT, " +
                        "title TEXT, " +
                        "content TEXT, " +
                        "attachment TEXT);";
        db.execSQL(str);
    }

    public void save(Data data){

        if(data.id.equals("")){
            insert(data);
        }else{
            update(data);
        }
    }

    public void insert(Data data){
        String str =
                "INSERT INTO DiaryDB VALUES(" +
                        "null, " +
                        "'"+data.year+"', " +
                        "'"+data.month+"', " +
                        "'"+data.day+"', " +
                        "'"+data.emoji+"', " +
                        "'"+data.title+"', " +
                        "'"+data.content+"', " +
                        "'"+data.attachment+"');";
        callDB(str);
    }

    public void update(Data data){
        String str =
                "UPDATE DiaryDB SET " +
                        "year = '"+ data.year +"', " +
                        "month = '"+ data.month +"', " +
                        "day = '"+ data.day +"', " +
                        "emoji = '"+ data.emoji +"', " +
                        "title = '"+ data.title +"', " +
                        "content = '"+ data.content +"', " +
                        "attachment = '"+ data.attachment +"' " +
                        "WHERE numID ='"+ data.id +"';";
        callDB(str);
    }

    public void order(){
        String str =
                "Select * from DiaryDB Order by numID";

        callDB(str);
    }

    public void delete(int id){
        String str =
                "DELETE FROM DiaryDB WHERE numID= '"+ id + "';";
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
                "Select numID, year, month, day, emoji, title, content, attachment From DiaryDB";
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
                "Select numID, year, month, day, emoji, title, content, attachment From " + table + " where numID = " + id ;
        Cursor cursor = db.rawQuery(str,null);
        cursor.moveToFirst();

        return cursor;
    }
}
