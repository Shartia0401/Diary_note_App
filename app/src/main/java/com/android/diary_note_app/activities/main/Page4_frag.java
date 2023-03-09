package com.android.diary_note_app.activities.main;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.frag.recycler.Recycle_grid;

public class Page4_frag extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page4_frag,container,false);
        setBtn(v);
        return v;
    }

    public void setBtn(View v){
        recyclerView = v.findViewById(R.id.frag4_recycle);
        new Recycle_grid(recyclerView, getActivity(), 4);
    }


}