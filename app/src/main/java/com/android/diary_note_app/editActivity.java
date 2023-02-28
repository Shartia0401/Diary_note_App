package com.android.diary_note_app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.diary_note_app.activities.edit.ScrollingFragment;
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
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        ScrollingFragment fragment = new ScrollingFragment();


        if(getIntent() != null){
            Bundle bundle = getIntent().getBundleExtra("data");
            fragment.setArguments(bundle);
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content, fragment);
        fragmentTransaction.commit();

        applyColors();
    }

    private void applyColors() {
        getWindow().setStatusBarColor(Color.parseColor("#FFBB66"));
        getWindow().setNavigationBarColor(Color.parseColor("#FFBB66"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "뒤로가기버튼이 눌렸습니다", Toast.LENGTH_LONG).show();
            finish();
            return true;
        }
        return false;
    }
}
