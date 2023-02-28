package com.android.diary_note_app.activities.ui.recycler;

import android.content.Context;
import android.database.Cursor;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.diary_note_app.db_helper.DB_helper;

import java.util.ArrayList;

public class Recycle_grid{
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    frag4_RecyclerViewAdapter frag4recyclerViewAdapter;
    GridLayoutManager layoutManager;

    public Recycle_grid(RecyclerView recyclerView_in, Context context, int frag){
        ArrayList<Item> list = setList(context);
        recyclerView = recyclerView_in;

        layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);

        if(frag == 1){
            adapter = new RecyclerViewAdapter(context, list);
            recyclerView.setAdapter(adapter);
        }else{
            frag4recyclerViewAdapter = new frag4_RecyclerViewAdapter(context, list);
            recyclerView.setAdapter(frag4recyclerViewAdapter);
        }
    }

    private ArrayList<Item> setList(Context context){
        ArrayList<Item> list = new ArrayList<Item>();

        DB_helper dbHelper = new DB_helper(context);
        Cursor cursor = dbHelper.getList();
        cursor.moveToFirst();

        int count = cursor.getCount();

        for(int i = 0; i < count; ++i){
            String Id = cursor.getString(0);
            String date = cursor.getString(1);
            String emoji = cursor.getString(2);
            String name = cursor.getString(3);
            String content = cursor.getString(4);
            String path = cursor.getString(5);
            list.add(new Item(date, emoji, name, content, path,Id));
            cursor.moveToNext();
        }

        return list;
    }
}
