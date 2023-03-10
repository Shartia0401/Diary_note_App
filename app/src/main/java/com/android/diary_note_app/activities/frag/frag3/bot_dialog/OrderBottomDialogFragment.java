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
import com.android.diary_note_app.activities.frag.recycler.OpenEditAct;
import com.android.diary_note_app.activities.frag.recycler.frag2.DayDiaryItem;
import com.android.diary_note_app.activities.frag.recycler.frag2.Frag2Adapter;
import com.android.diary_note_app.db_helper.DB_helper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class OrderBottomDialogFragment extends BottomSheetDialogFragment implements OnItemClickListener{

    RecyclerView recyclerView;
    Frag2Adapter adapter;
    View v;

    ArrayList<DayDiaryItem> list;


    public OrderBottomDialogFragment(ArrayList<DayDiaryItem> list){
        this.list = list;
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
        recyclerView = v.findViewById(R.id.frag3_bottom_RecyclerView);
        adapter = new Frag2Adapter(v.getContext());
        adapter.setDiaryList(list);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

    }


    @Override
    public void onItemClick(String id) {
        dismiss();
        new OpenEditAct(v, id);
    }
}
