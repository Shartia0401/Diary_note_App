package com.android.diary_note_app.activities.edit;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.diary_note_app.R;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class StyleSpinner {

    ArrayList<String> str_list;
    Spinner spinner;
    View view;
    String[] fileNames;

    public StyleSpinner(View view){
        this.view = view;
        getFontList();
        setSpinner();
    }

    private void setSpinner() {
        spinner = view.findViewById(R.id.edit_spiner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                view.getContext(), android.R.layout.simple_spinner_item,fileNames);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d("test", fileNames[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("test", "아무것도 선택하지 않았습니다");
            }
        });

    }

    private File[] getFontList(){
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

        return files;
    }
}
