package com.android.diary_note_app.activities.main;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.frag.frag3.picker.YMPickerListener;
import com.android.diary_note_app.activities.frag.frag3.picker.YMPickerPopupDialogTwoButton;
import com.android.diary_note_app.db_helper.DB_helper;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

public class Page3_frag extends Fragment {

    TextView date_Tv;
    TextView happy_Tv;
    TextView ache_Tv;
    TextView angry_Tv;

    int happy, ache, angry;
    CalendarDay date;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_page3_frag,container,false);

        refrash();
        setDate();
        setDialog();

        return v;
    }

    private void refrash(){
        date_Tv = v.findViewById(R.id.date_Tv);
        happy_Tv = v.findViewById(R.id.frag3_happy_tv);
        ache_Tv = v.findViewById(R.id.frag3_ache_tv);
        angry_Tv = v.findViewById(R.id.frag3_angry_tv);
    }

    public void setDate(){
        String today = date.getYear() + "년 " +date.getMonth()+ "월";
        date_Tv.setText(today);
    }

    public void setDialog(){
        date_Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YMPickerPopupDialogTwoButton pickerDialog = new YMPickerPopupDialogTwoButton(view.getContext(), new YMPickerListener() {
                    @Override
                    public void onPositiveClick(int h, int m) {
                        System.err.println("아휴.." + h + " " + m);
                        date_Tv.setText(h + "년 " + m + "월");
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });

                pickerDialog.setCanceledOnTouchOutside(false);
                pickerDialog.setCancelable(true);
                pickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                pickerDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                pickerDialog.show();
            }
        });
    }

    private void setEmojiList(int year, int month){
        ArrayList<String[]> emojiList = new ArrayList<>();
        DB_helper dbHelper = new DB_helper(getContext());
        Cursor cursor = dbHelper.getList(year, month);
        cursor.moveToFirst();

        happy = 0;
        ache = 0;
        angry = 0;

        for(int i = 0; i < cursor.getCount(); ++i){
            String numID = cursor.getString(0);
            String emoji = cursor.getString(1);
            String[] set = {numID , emoji};
            if(emoji.equals("null")){
                classification(emoji);
                emojiList.add(set);
            }
            cursor.moveToNext();
        }
        dbHelper.close();

        happy_Tv.setText(happy);
        ache_Tv.setText(ache);
        angry_Tv.setText(angry);

    }

    private void classification(String emoji){

        switch (emoji){
            case "happy":
                happy += 1;
                break;
            case "ache":
                ache += 1;
            case "angry":
                angry += 1;
        }
    }

}