package com.android.diary_note_app.db_helper;

public class Data {

    public int year, month, day;
    public String id, emoji, title, content, attachment;

    public Data(String id, int year, int month, int day, String emoji, String title, String content, String attachment){
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.emoji = emoji;
        this.title = title;
        this.content = content;
        this.attachment = attachment;
    }

}
