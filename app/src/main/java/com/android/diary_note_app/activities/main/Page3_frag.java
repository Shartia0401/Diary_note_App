package com.android.diary_note_app.activities.main;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.android.diary_note_app.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class Page3_frag extends Fragment {

    Button button;

    CalendarDay date;
    boolean isfirst = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page3_frag,container,false);
        setDate(v);
        return v;
    }

    public void setDate(View v){

        button = v.findViewById(R.id.date_button);
        date = CalendarDay.today();
        String today = date.getYear() + "년 " +date.getMonth()+ "월 "+ date.getDay()+ "일";
        button.setText(today);
    }
}