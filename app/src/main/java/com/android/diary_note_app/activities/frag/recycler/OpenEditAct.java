package com.android.diary_note_app.activities.frag.recycler;

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
    String id;

    public OpenEditAct(View view, String id){
        this.view = view;
        this.id = id;
        setAct();

    }

    private void setAct(){
        Intent intent = new Intent(view.getContext(), editActivity.class);
        intent.putExtra("data", getData());
        if(!isException){
            view.getContext().startActivity(intent);
        }else{
            intent = new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(intent);
        }

    }

    @NonNull
    private Bundle getData(){

        db_helper = new DB_helper(view.getContext());
        Bundle bundle = new Bundle();

        Cursor[] cursor = db_helper.getData(id);
        cursor[0].moveToFirst();
        String[] a = new String[8];

        try{
            for(int i = 0; i < a.length; i++){
                a[i] = cursor[0].getString(i);
            }
        }catch (CursorIndexOutOfBoundsException e){
            isException = true;
        }

        bundle.putString("id", a[0]);

        return bundle;
    }

}
