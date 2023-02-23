package com.android.diary_note_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.diary_note_app.R;
import com.android.diary_note_app.db_helper.DB_helper;

public class editActivity extends AppCompatActivity {

    DB_helper db_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setBtn();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected( item);
        }
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

    private void setBtn(){
        EditText title_ET = findViewById(R.id.title_edit);
        EditText content_ET = findViewById(R.id.content_edit);


//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String title = title_ET.getText().toString();
//                String content = content_ET.getText().toString();
//
//                db_helper = new DB_helper(getApplicationContext());
//                db_helper.insert(null, title, null, content, null);
//
//            }
//        });
    }
}