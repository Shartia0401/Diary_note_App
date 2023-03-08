package com.android.diary_note_app.activities.frag.recycler.frag2;

public class DayDiaryItem {
    String id;
    String emoji;
    String title;
    String content;
    String imagePath;

    public DayDiaryItem(String id, String emoji, String title, String content, String imagePath){
        this.id = id;
        this.emoji = emoji;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
    }

    public String getId(){
        return id;
    }

}
