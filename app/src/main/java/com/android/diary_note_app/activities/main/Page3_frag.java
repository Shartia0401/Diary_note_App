package com.android.diary_note_app.activities.main;

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
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.ui.calender.SelectDate;
import com.android.diary_note_app.activities.ui.picker.YMPickerListener;
import com.android.diary_note_app.activities.ui.picker.YMPickerPopupDialogTwoButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class Page3_frag extends Fragment {

    TextView date_Tv;
    YMPickerPopupDialogTwoButton ymPicker;
    int year;
    int month;
    CalendarDay date;
    boolean isfirst = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page3_frag,container,false);
        setDate(v);
        setDialog();
        return v;
    }

    public void setDate(View v){
        date_Tv = v.findViewById(R.id.date_Tv);
        date = CalendarDay.today();
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


}