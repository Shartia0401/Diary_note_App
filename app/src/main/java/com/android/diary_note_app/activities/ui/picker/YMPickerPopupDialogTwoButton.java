package com.android.diary_note_app.activities.ui.picker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.diary_note_app.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class YMPickerPopupDialogTwoButton extends Dialog {

    private Context context;
    private YMPickerListener listener;
    private NumberPicker year, month;

    private Button tvPositive, tvNegative;

    private int setYearValue;
    private int setMonthValue;

    public YMPickerPopupDialogTwoButton(@NonNull Context context, YMPickerListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    public void setYear(int setYearValue){
        this.setYearValue = setYearValue;
    }
    public void setMonth(int setMonthValue){
        this.setMonthValue = setMonthValue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year_month_selection_dialog);
        year = (NumberPicker) findViewById(R.id.yearPicker);
        month = (NumberPicker) findViewById(R.id.monthPicker);
        year.setMinValue(1900);
        year.setMaxValue(2100);
        month.setMinValue(1);
        month.setMaxValue(12);
        setYearValue = CalendarDay.today().getYear();
        setMonthValue = CalendarDay.today().getMonth();
        year.setValue(CalendarDay.today().getYear());
        month.setValue(CalendarDay.today().getMonth());
        year.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int Val) {
                setYearValue = Val;
            }
        });
        month.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int Val) {
                setMonthValue = Val;
            }
        });
        tvPositive = findViewById(R.id.time_btn_yes);
        tvPositive.setOnClickListener(v -> {
            this.listener.onPositiveClick(setYearValue,setMonthValue);
            dismiss();
        });
        tvNegative = findViewById(R.id.time_btn_no);
        tvNegative.setOnClickListener(v -> {
            this.listener.onNegativeClick();
            dismiss();
        });
    }
}
