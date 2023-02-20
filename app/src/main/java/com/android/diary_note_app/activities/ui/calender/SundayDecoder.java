package com.android.diary_note_app.activities.ui.calender;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.threeten.bp.DayOfWeek;

import java.util.Calendar;

public class SundayDecoder implements DayViewDecorator {
    private final Calendar calendar = Calendar.getInstance();
    public SundayDecoder(){
    }
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        int sunday = day.getDate().with(DayOfWeek.SUNDAY).getDayOfMonth();
        return sunday == day.getDay();
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.rgb(255,5,98)));
    }
}
