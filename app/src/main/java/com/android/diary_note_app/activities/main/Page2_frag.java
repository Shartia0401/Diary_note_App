package com.android.diary_note_app.activities.main;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.frag.frag2.calender.OneDayDecorator;
import com.android.diary_note_app.activities.frag.frag2.calender.SundayDecoder;
import com.android.diary_note_app.activities.frag.frag3.bot_dialog.OnItemClickListener;
import com.android.diary_note_app.activities.frag.recycler.OpenEditAct;
import com.android.diary_note_app.activities.frag.recycler.frag2.DayDiaryItem;
import com.android.diary_note_app.activities.frag.recycler.frag2.Frag2Adapter;
import com.android.diary_note_app.db_helper.DB_helper;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;

public class Page2_frag extends Fragment implements OnItemClickListener {
    MaterialCalendarView materialCalendarView;


    String currentDate;
    RecyclerView recyclerView;
    Frag2Adapter adapter;
    View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_page2_frag,container,false);
        setCalendar();
        setRecycler();

        return v;
    }

    private void setRecycler(){
        recyclerView = v.findViewById(R.id.frag2_recycler);
        adapter = new Frag2Adapter(v.getContext());
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
    }

    private void setClickEvent(Cursor cursor){
        setRecycler();
        ArrayList<DayDiaryItem> list = new ArrayList<>();

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            for(int i = 0; i < cursor.getCount(); ++i){
                list.add(getDiaryItem(cursor));
                cursor.moveToNext();
            }

        }

        adapter.setDiaryList(list);
    }

    private DayDiaryItem getDiaryItem(Cursor cursor){
        String id = cursor.getString(0);
        String title = cursor.getString(1);
        String content = cursor.getString(2);
        String imagePath = cursor.getString(3);
        String emoji = cursor.getString(4);

        return new DayDiaryItem(id, title, content, imagePath, emoji);
    }


    private Cursor dbList(int year, int month, int day){
        DB_helper dbHelper = new DB_helper(v.getContext());
        Cursor cursor = dbHelper.getList(year, month, day);
        dbHelper.close();

        return cursor;
    }


    private void setCalendar(){

        materialCalendarView = v.findViewById(R.id.Calender);

        materialCalendarView.addDecorators(
                new SundayDecoder(),
                new OneDayDecorator()
        );
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDay();

                String a = year + "??? " +month+ "??? " + day + "???";
                currentDate = a;

                setClickEvent(dbList(date.getYear(), date.getMonth(), date.getDay()));

            }
        });

    }


    @Override
    public void onItemClick(String id) {
        new OpenEditAct(v, id);
    }
}