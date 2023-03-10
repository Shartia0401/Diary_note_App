package com.android.diary_note_app.activities.frag.frag3.bot_dialog;

import android.database.Cursor;

import com.android.diary_note_app.activities.frag.recycler.OpenEditAct;
import com.android.diary_note_app.activities.frag.recycler.frag2.DayDiaryItem;
import com.android.diary_note_app.activities.main.Page3_frag;
import com.android.diary_note_app.db_helper.DB_helper;

import java.util.ArrayList;

public class Bot_dialog_set{

    ArrayList<DayDiaryItem> ItemList;

    DB_helper dbHelper ;
    Page3_frag frag;
    String[] list;
    public Bot_dialog_set(Page3_frag frag, String[] list){
        this.frag = frag;
        this.list = list;
        dbHelper = new DB_helper(frag.getContext());
    }

    public void setDialog(){
        setList();
        OrderBottomDialogFragment orderBottomDialogFragment = new OrderBottomDialogFragment(ItemList);
        orderBottomDialogFragment.show(frag.getParentFragmentManager(), orderBottomDialogFragment.getTag());
    }
    private void setList(){
        ItemList = new ArrayList<>();

        for(String id : list){
            Cursor cursor = dbList(id);
            cursor.moveToFirst();
            if(cursor.getCount() > 0){
                ItemList.add(getDiaryItem(cursor));
            }
            cursor.close();
        }

        dbHelper.close();
    }

    private DayDiaryItem getDiaryItem(Cursor cursor){
        String id = cursor.getString(0);
        String title = cursor.getString(5);
        String content = cursor.getString(6);
        String imagePath = cursor.getString(7);
        String emoji = cursor.getString(4);

        return new DayDiaryItem(id, emoji, title, content, imagePath);
    }

    private Cursor dbList(String id){
        Cursor[] cursor_l = dbHelper.getData(id);
        Cursor cursor = cursor_l[0];



        return cursor;
    }



}
