package com.android.diary_note_app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTabLayout();
        fabAct();
        applyColors();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        setTabLayout();
        fabAct();
        applyColors();

    }

    // Apply the title/navigation bar color
    private void applyColors() {

        getWindow().setStatusBarColor(Color.parseColor("#FFBB66"));
        getWindow().setNavigationBarColor(Color.parseColor("#FFBB66"));
    }

    private void fabAct(){
        FloatingActionButton fab = findViewById(R.id.fb);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),editActivity.class);
            startActivity(intent);

        });
    }

    private void setTabLayout(){
        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("최신"));
        tabLayout.addTab(tabLayout.newTab().setText("달력"));
        tabLayout.addTab(tabLayout.newTab().setText("기분"));
        tabLayout.addTab(tabLayout.newTab().setText("사진"));

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewAdapter(getSupportFragmentManager()));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        int i = 0;

        if(viewPager != null){

        }
    }
}