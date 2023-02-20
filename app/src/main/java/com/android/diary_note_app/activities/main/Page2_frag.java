package com.android.diary_note_app.activities.main;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.ui.calender.OneDayDecorator;
import com.android.diary_note_app.activities.ui.calender.SelectDate;
import com.android.diary_note_app.activities.ui.calender.SundayDecoder;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class Page2_frag extends Fragment {
    MaterialCalendarView materialCalendarView;
    TextView textView;

    String currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page2_frag,container,false);
        materialCalendarView = v.findViewById(R.id.Calender);
        textView = v.findViewById(R.id.CalenderTv);
        try {
            textView.setText(currentDate);
        }catch (NullPointerException e){
            System.err.println(e.getMessage());
        }
        materialCalendarView.addDecorators(
                new SundayDecoder(),
                new OneDayDecorator(),
                new SelectDate(getContext())
        );
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDay();

                String a = year + "년 " +month+ "월 " + day + "일";
                currentDate = a;
                textView.setText(currentDate);
            }
        });

        return v;
    }



}