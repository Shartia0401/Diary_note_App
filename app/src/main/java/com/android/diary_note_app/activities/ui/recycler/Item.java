package com.android.diary_note_app.activities.ui.recycler;

import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.android.diary_note_app.R;

import java.io.IOException;

public class Item {

    String date;
    String Id;
    String emoji;
    String name;
    String content;
    String attachment;
    String image;

    public Item(String date, String emoji, String name, String content, String image, String Id) {
        super();
        this.date = date;
        this.emoji = emoji;
        this.name = name;
        this.content = content;
        this.image = image;
        this.Id = Id;
    }



    private void attachmentFilter(){

    }


}