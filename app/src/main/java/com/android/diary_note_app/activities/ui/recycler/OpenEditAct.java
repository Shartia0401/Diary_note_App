package com.android.diary_note_app.activities.ui.recycler;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.diary_note_app.MainActivity;
import com.android.diary_note_app.R;
import com.android.diary_note_app.db_helper.DB_helper;
import com.android.diary_note_app.editActivity;

public class OpenEditAct {

    DB_helper db_helper;
    View view;
    boolean isException;

    public OpenEditAct(View view, int frag){
        this.view = view;
        setAct(frag);

    }

    private void setAct(int frag){
        Intent intent = new Intent(view.getContext(), editActivity.class);
        intent.putExtra("data", getData(frag));
        if(!isException){
            view.getContext().startActivity(intent);
        }else{
            intent = new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(intent);
        }

    }

    @NonNull
    private Bundle getData(int frag){
        TextView id_TV;

        if(frag == 1){
            id_TV = view.findViewById(R.id.frag1_id);
        }else{
            id_TV = view.findViewById(R.id.frag4_IdTextView);
        }

        db_helper = new DB_helper(view.getContext());
        Bundle bundle = new Bundle();

        String id = id_TV.getText().toString();

        Cursor cursor = db_helper.getData("DiaryDB",id);
        cursor.moveToFirst();
        String[] a = new String[8];

        try{
            for(int i = 0; i < a.length; i++){
                a[i] = cursor.getString(i);
            }
        }catch (CursorIndexOutOfBoundsException e){
            isException = true;
        }

        bundle.putString("id", a[0]);
        bundle.putString("year", a[1]);
        bundle.putString("month", a[2]);
        bundle.putString("day", a[3]);
        bundle.putString("emoji", a[4]);
        bundle.putString("title", a[5]);
        bundle.putString("content", a[6]);
        bundle.putString("attachment", a[7]);

        return bundle;
    }
}
