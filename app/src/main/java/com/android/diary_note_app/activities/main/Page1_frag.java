package com.android.diary_note_app.activities.main;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.frag.recycler.Recycle_grid;
import com.android.diary_note_app.activities.frag.recycler.RecyclerViewEmptySupport;

public class Page1_frag extends Fragment {

    RecyclerViewEmptySupport recyclerView;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_page1_frag,container,false);
        setRecyclerView();
        return v;
    }

    public void setRecyclerView() {
        TextView list_empty = v.findViewById(R.id.frag1_empty);
        recyclerView = v.findViewById(R.id.recycle);
        recyclerView.setEmptyView(list_empty);

        new Recycle_grid(recyclerView, getActivity(), 1);

    }
}