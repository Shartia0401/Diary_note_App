package com.android.diary_note_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.android.diary_note_app.R;

public class editActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode ==KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "뒤로가기버튼이 눌렸습니다",Toast.LENGTH_LONG).show();
            finish();
            return true;
        }

        return false;
    }
}