package com.android.diary_note_app;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.Permission;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        ImageView imageView = findViewById(R.id.start_imageview);
//        imageView.setOutlineProvider(new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
//                outline.setRoundRect(0,0, view.getWidth(), view.getHeight(), 40);
//            }
//        });
//
//        imageView.setClipToOutline(true);
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},10);
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }


}