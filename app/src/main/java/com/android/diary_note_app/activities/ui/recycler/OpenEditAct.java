package com.android.diary_note_app.activities.ui.recycler;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.diary_note_app.R;
import com.android.diary_note_app.db_helper.DB_helper;
import com.android.diary_note_app.editActivity;

public class OpenEditAct {

    DB_helper db_helper;
    View view;

    public OpenEditAct(View view){
        this.view = view;
        setAct();
    }

    private void setAct(){
        Intent intent = new Intent(view.getContext(), editActivity.class);
        intent.putExtra("data", getData());
        view.getContext().startActivity(intent);

    }

    @NonNull
    private Bundle getData(){
        TextView id_TV = view.findViewById(R.id.frag1_id);
        db_helper = new DB_helper(view.getContext());
        Bundle bundle = new Bundle();

        String id = id_TV.getText().toString();


        Cursor cursor = db_helper.getData("DiaryDB",id);
        cursor.moveToFirst();
        String[] a = new String[6];

        for(int i = 0; i < a.length; i++){
            a[i] = cursor.getString(i);
        }

        bundle.putString("id", a[0]);
        bundle.putString("date", a[1]);
        bundle.putString("emoji", a[2]);
        bundle.putString("title", a[3]);
        bundle.putString("content", a[4]);
        bundle.putString("attachment", a[5]);
        System.err.println(a[5]);

        return bundle;
    }

}
