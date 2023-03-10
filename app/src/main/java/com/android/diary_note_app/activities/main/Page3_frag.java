package com.android.diary_note_app.activities.main;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.frag.frag3.bot_dialog.Bot_dialog_set;
import com.android.diary_note_app.activities.frag.frag3.bot_dialog.OnItemClickListener;
import com.android.diary_note_app.activities.frag.frag3.bot_dialog.OrderBottomDialogFragment;
import com.android.diary_note_app.activities.frag.frag3.picker.YMPickerListener;
import com.android.diary_note_app.activities.frag.frag3.picker.YMPickerPopupDialogTwoButton;
import com.android.diary_note_app.activities.frag.recycler.frag2.DayDiaryItem;
import com.android.diary_note_app.db_helper.DB_helper;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.HashMap;

public class Page3_frag extends Fragment{

    TextView date_Tv;
    TextView happy_Tv;
    TextView ache_Tv;
    TextView angry_Tv;
    int index;

    ArrayList<String[]> emojiList;

    int happy, ache, angry;
    CalendarDay date;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_page3_frag,container,false);

        refresh();
        setDate();
        setDialog();
        setEmojiList(CalendarDay.today().getYear(), CalendarDay.today().getMonth());
        imgBtnOnClick();

        return v;
    }

    private void refresh(){
        date_Tv = v.findViewById(R.id.date_Tv);
        happy_Tv = v.findViewById(R.id.frag3_happy_tv);
        ache_Tv = v.findViewById(R.id.frag3_ache_tv);
        angry_Tv = v.findViewById(R.id.frag3_angry_tv);
        date = CalendarDay.today();
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
                        setEmojiList(h, m);
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
        emojiList = new ArrayList<>();
        DB_helper dbHelper = new DB_helper(getContext());
        Cursor cursor = dbHelper.getList(year, month);
        cursor.moveToFirst();

        happy = 0;
        ache = 0;
        angry = 0;

        boolean a = true;
        while (cursor.moveToNext()) {
            if(a){
                cursor.moveToFirst();
                a = false;
            }
            String numID = cursor.getString(0);
            String emoji = cursor.getString(1);
            String[] set = {numID, emoji};

            if (!emoji.equals("null")) {
                classification_plus(emoji);
                emojiList.add(set);
            }
        }
        cursor.close();
        dbHelper.close();

        String happy_s = Integer.toString(happy);
        String ache_s = Integer.toString(ache);
        String angry_s = Integer.toString(angry);

        happy_Tv.setText(happy_s);
        ache_Tv.setText(ache_s);
        angry_Tv.setText(angry_s);
    }
    private void imgBtnOnClick(){
        ImageView happy_img = v.findViewById(R.id.frag3_happy);
        ImageView ache_img = v.findViewById(R.id.frag3_ache);
        ImageView angry_img = v.findViewById(R.id.frag3_angry);
        happy_img.setTag("happy");
        ache_img.setTag("ache");
        angry_img.setTag("angry");

        onClick(happy_img);
        onClick(ache_img);
        onClick(angry_img);
    }
    private void onClick(ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBottom(imageView);
            }
        });
    }

    private void setBottom(ImageView imageView){
        String[] list = setBottomNavigation(imageView.getTag().toString());
        Bot_dialog_set botDialogSet = new Bot_dialog_set(this, list);
        botDialogSet.setDialog();

    }

    private void classification_plus(String emoji){
        switch (emoji){
            case "happy":
                ++happy;
                break;
            case "ache":
                ++ache;
                break;
            case "angry":
                ++angry;
                break;
        }
    }

    private String[] setBottomNavigation(String emj){
        int length;

        switch (emj){
            case "happy":
                length = happy;
                break;
            case "ache":
                length = ache;
                break;
            case "angry":
                length = angry;
                break;
            default:
                length = 0;
                break;
        }

        String[] idList = new String[length];
        index = idList.length-1;
        for(String[] str : emojiList){
            if(str[1].equals(emj)){
                idList[index] = str[0];
                index--;
            }
        }

        for(String id : idList){
            Log.d("test", id);
        }

        return idList;
    }

}