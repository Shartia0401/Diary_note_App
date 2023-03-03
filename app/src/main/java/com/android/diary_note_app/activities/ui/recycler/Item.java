package com.android.diary_note_app.activities.ui.recycler;

public class Item {

    int year, month, day;
    String Id;
    String emoji;
    String name;
    String content;
    String attachment;
    String image;
    int position;

    public Item(int year, int month, int day, String emoji, String name, String content, String image, String Id) {
        super();
        this.year = year;
        this.month = month;
        this.day = day;
        this.emoji = emoji;
        this.name = name;
        this.content = content;
        this.image = image;
        this.Id = Id;
    }

    public void setPosition(int p){
        this.position = p;
    }

    public int getPosition(){
        return position;
    }


    private void attachmentFilter(){

    }


}