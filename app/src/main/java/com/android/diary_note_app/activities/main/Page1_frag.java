package com.android.diary_note_app.activities.main;

import android.Manifest;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.ui.recycler.Recycle_grid;

public class Page1_frag extends Fragment {

    RecyclerView recyclerView;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_page1_frag,container,false);
        setRecyclerView();
        return v;
    }

    public void setRecyclerView() {
        recyclerView = v.findViewById(R.id.recycle);
        new Recycle_grid(recyclerView, getActivity(), 1);

    }
}