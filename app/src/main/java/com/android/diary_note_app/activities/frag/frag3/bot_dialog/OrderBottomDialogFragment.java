package com.android.diary_note_app.activities.frag.frag3.bot_dialog;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.frag.recycler.frag2.DayDiaryItem;
import com.android.diary_note_app.activities.frag.recycler.frag2.Frag2Adapter;
import com.android.diary_note_app.db_helper.DB_helper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class OrderBottomDialogFragment extends BottomSheetDialogFragment {
    private OnItemClickListener listener;
    RecyclerView recyclerView;
    Frag2Adapter adapter;
    View v;
    DayDiaryItem list;
    public OrderBottomDialogFragment(DayDiaryItem list){
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag3_bottom_dialog, container, false);
        setRecycler();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void setRecycler(){
        recyclerView = v.findViewById(R.id.frag2_recycler);
        adapter = new Frag2Adapter(v.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
    }

    private void setClickEvent(Cursor cursor){
        setRecycler();
        ArrayList<DayDiaryItem> list = new ArrayList<>();

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            for(int i = 0; i < cursor.getCount(); ++i){
                list.add(getDiaryItem(cursor));
                cursor.moveToNext();
            }

        }

        adapter.setDiaryList(list);
    }

    private DayDiaryItem getDiaryItem(Cursor cursor){
        String id = cursor.getString(0);
        String title = cursor.getString(1);
        String content = cursor.getString(2);
        String imagePath = cursor.getString(3);
        String emoji = cursor.getString(4);

        return new DayDiaryItem(id, title, content, imagePath, emoji);
    }


    private Cursor dbList(int year, int month, int day){
        DB_helper dbHelper = new DB_helper(v.getContext());
        Cursor cursor = dbHelper.getList(year, month, day);
        dbHelper.close();

        return cursor;
    }

}
