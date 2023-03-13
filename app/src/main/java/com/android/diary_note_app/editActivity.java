package com.android.diary_note_app;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.diary_note_app.activities.edit.ScrollingFragment;

public class editActivity extends AppCompatActivity {

    View.OnClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);
        ActionBar top_actionBar = getSupportActionBar();
        assert top_actionBar != null;
        top_actionBar.setDisplayHomeAsUpEnabled(true);

        ScrollingFragment fragment = new ScrollingFragment();
        setbtn();

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

    public void setbtn(){
        Button button = findViewById(R.id.edit_toolbar_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener != null){
                    clickListener.onClick(view);
                }
            }
        });

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

    public void setOnclick(View.OnClickListener listener){
        this.clickListener = listener;
    }


}
