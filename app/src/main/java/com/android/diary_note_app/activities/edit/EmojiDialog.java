package com.android.diary_note_app.activities.edit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.edit.listener.OnEmojiSelectedListener;

public class EmojiDialog extends Dialog {

    private Context context;

    private OnEmojiSelectedListener listener;

    public EmojiDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public void setOnEmojiSelectedListener(OnEmojiSelectedListener listener){
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emoji_select_dialog);

        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(new RoundRectShape(new float[]{30, 30, 30, 30, 30, 30, 30, 30}, null, null));
        shapeDrawable.getPaint().setColor(Color.WHITE);
        this.getWindow().setBackgroundDrawable(shapeDrawable);
        setButton();
    }



    private void setButton(){
        ImageButton happy_btn, ache_btn, angry_btn;
        happy_btn = findViewById(R.id.emoji_btn_happy);
        ache_btn = findViewById(R.id.emoji_btn_ache);
        angry_btn = findViewById(R.id.emoji_btn_angry);

        happy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int emoji = R.drawable.emoji_happy;

                if(listener != null){
                    listener.onEmojiSelected(emoji, "happy");
                    dismiss();
                }
            }
        });

        ache_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int emoji = R.drawable.emoji_ache;
                if(listener != null){
                    listener.onEmojiSelected(emoji, "ache");
                    dismiss();
                }
            }
        });

        angry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int emoji = R.drawable.emoji_angry;
                if(listener != null){
                    listener.onEmojiSelected(emoji, "angry");
                    dismiss();
                }
            }
        });
    }
}
