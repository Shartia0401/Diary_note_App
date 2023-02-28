package com.android.diary_note_app.activities.main;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.ui.recycler.Recycle_grid;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Page4_frag extends Fragment {
    View v;
    Uri imagePath;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_page4_frag,container,false);


        setBtn(v);
        return v;
    }

    public void setBtn(View v){
        recyclerView = v.findViewById(R.id.frag4_recycle);
        new Recycle_grid(recyclerView, getActivity(), 4);

    }


}