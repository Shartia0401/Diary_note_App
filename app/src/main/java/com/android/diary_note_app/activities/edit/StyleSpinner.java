package com.android.diary_note_app.activities.edit;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.edit.listener.OnSelectFontListener;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class StyleSpinner {


    ArrayList<String> str_list;
    Spinner spinner;
    View v;
    String[] fileNames;

    ArrayAdapter<String> adapter;

    OnSelectFontListener onSelectFontListener;

    public StyleSpinner(View view){
        this.v = view;
        setFontList();
        setSpinner();
    }

    public void SetOnSelectFontListener(OnSelectFontListener onSelectFontListener){
        this.onSelectFontListener = onSelectFontListener;
    }

    private void setSpinner() {
        spinner = v.findViewById(R.id.edit_spiner);
       adapter = new ArrayAdapter<String>(
                v.getContext(), android.R.layout.simple_spinner_item,fileNames);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView textView = v.findViewById(R.id.edit_content);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("test", fileNames[position]);
                if(onSelectFontListener != null){
                    onSelectFontListener.onSelect(fileNames[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("test", "아무것도 선택하지 않았습니다");
            }
        });

    }

    private void setFontList(){
        String filePath = "/system/fonts/";
        File dir = new File(filePath);
        FileFilter fileFilter = file -> file.getName().endsWith("ttf");
        File[] files = dir.listFiles(fileFilter);

        assert files != null;
        for(File file : files){
            Log.d("test", file.toString() + "파일");
        }

        fileNames = new String[files.length-1];
        for(int i = 0; i < files.length-1; ++i){
            String[] str = files[i].getName().split("\\.");
            fileNames[i] = str[0];
        }

    }

    public void setFont(String font){
        int initPosition = adapter.getPosition(font);
        spinner.setSelection(initPosition);
    }
}
