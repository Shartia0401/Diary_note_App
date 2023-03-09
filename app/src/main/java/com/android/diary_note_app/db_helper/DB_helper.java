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
        setStyleTB(db);

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
    private void setStyleTB(SQLiteDatabase db){
        String str =
                "CREATE TABLE StyleDB (" +
                        "numID TEXT, " +
                        "font TEXT, " +
                        "size TEXT, " +
                        "color TEXT);";
        db.execSQL(str);
    }
    private void style_Insert(String numID, Data data){
        String str =
                "INSERT INTO StyleDB VALUES(" +
                        "'"+numID+"', " +
                        "'"+data.font+"', " +
                        "'"+data.size+"', " +
                        "'"+data.color+"');";
        callDB(str);
    }
    private void style_Update(Data data){
        String str =
                "UPDATE StyleDB SET " +
                        "font = '"+ data.font +"', " +
                        "size = '"+ data.size +"', " +
                        "color = '"+ data.color +"' " +
                        "WHERE numID ='"+ data.id +"';";
        callDB(str);
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

        Cursor cursor = getList();
        cursor.moveToLast();
        String id = cursor.getString(0);

        style_Insert(id, data);
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

        style_Update(data);
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
    public Cursor getList(int year, int month, int day){

        SQLiteDatabase db = getReadableDatabase();
        String str =
                "Select numID, emoji, title, content, attachment From DiaryDB where year = '"+year +"' AND month = '"+month+"' AND day = '"+day+"';";
        Cursor cursor = db.rawQuery(str,null);
        cursor.moveToFirst();

        return cursor;
    }
    public Cursor getList(int year, int month){

        SQLiteDatabase db = getReadableDatabase();
        String str =
                "Select numID, emoji, title, content, attachment From DiaryDB where year = '"+year +"' AND month = '"+month+"';";
        Cursor cursor = db.rawQuery(str,null);
        cursor.moveToFirst();

        return cursor;
    }
    private void callDB(String str){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(str);
        db.close();
    }
    public Cursor[] getData(String id){
        SQLiteDatabase db = getReadableDatabase();
        String DiaryDB =
                "Select numID, year, month, day, emoji, title, content, attachment From DiaryDB where numID = " + id ;
        String styleDB =
                "Select numID, font, size, color From StyleDB where numID = " + id ;
        Cursor Diary = db.rawQuery(DiaryDB,null);
        Cursor style = db.rawQuery(styleDB ,null);

        return new Cursor[]{Diary, style};
    }
}
