package com.android.diary_note_app.activities.edit;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.edit.listener.OnEmojiSelectedListener;
import com.android.diary_note_app.db_helper.DB_helper;
import com.android.diary_note_app.db_helper.Data;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import java.io.IOException;
import java.util.Calendar;

public class ScrollingFragment extends Fragment implements OnEmojiSelectedListener {

    View v;
    Uri uri;
    String path;
    int year_i, month_i, day_i;

    EditText title_et;
    EditText content_et;
    TextView date_tv;
    TextView id_tv;
    String title;
    String content;
    CalendarDay today;
    ImageView imageView;

    String emoji_str;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_scrolling, container, false);
        refresh();
        setEmojiImg();
        if(getArguments() != null){
            setBundle(getArguments());
        }

        new StyleSpinner(v);
        return v;
    }

    private void setBundle(Bundle bundle){
        String id = bundle.getString("id");

        String year = bundle.getString("year");
        String month = bundle.getString("month");
        String day = bundle.getString("day");
        String str = year + "년 " + month + "월 " + day + "일";

        String title = bundle.getString("title");
        String content = bundle.getString("content");
        String path = bundle.getString("attachment");

        id_tv.setText(id);
        System.err.println(str);
        date_tv.setText(str);
        title_et.setText(title);
        content_et.setText(content);

        uri = Uri.parse(path);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(v.getContext().getContentResolver(), uri);
        } catch (IOException e) {
            System.err.println("파일 없음");
        }
        ImageView imageView = v.findViewById(R.id.edit_imageView);
        imageView.setImageBitmap(bitmap);
    }

    private void refresh(){
        title_et = v.findViewById(R.id.edit_title);
        content_et = v.findViewById(R.id.edit_content);
        id_tv = v.findViewById(R.id.edit_id);
        today = CalendarDay.today();
        setTextView();
        setDate(today.getYear(), today.getMonth(), today.getDay());
        setBtn();

    }

    private void setEmojiImg(){
        imageView = v.findViewById(R.id.edit_emojiImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialog();
            }
        });
    }

    private void setDialog(){
        EmojiDialog emojiDialog = new EmojiDialog(v.getContext());
        emojiDialog.setOnEmojiSelectedListener(this);
        emojiDialog.show();
    }

    @Override
    public void onEmojiSelected(int emoji, String str){
        imageView.setImageResource(emoji);
        emoji_str = str;
    }

    private void setTextView(){
        date_tv = v.findViewById(R.id.edit_TV);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        year_i = today.getYear();
        month_i = today.getMonth();
        day_i = today.getDay();

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (view, year, month, dayOfMonth) -> {
            setDate(year, month + 1, dayOfMonth);
            year_i = year;
            month_i = month + 1;
            day_i = dayOfMonth;
        }, mYear, mMonth, mDay);


        date_tv.setOnClickListener(view -> datePickerDialog.show());
    }

    private void setDate(int year, int month, int day){
        String str= year + "년 " + month+ "월 " + day + "일";
        date_tv.setText(str);
    }



    public void setBtn(){
        Button imageBtn = v.findViewById(R.id.edit_btn);
        Button saveBtn = v.findViewById(R.id.edit_savebtn);

        imageBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            intent.setAction(Intent.ACTION_PICK);
            activityResultLauncher.launch(intent);
            Toast.makeText(getContext(), "파일을 불러옵니다", Toast.LENGTH_SHORT).show();
        });

        saveBtn.setOnClickListener(view -> {
            DB_helper db_helper = new DB_helper(getContext());
            title = title_et.getText().toString();
            content = content_et.getText().toString();
            String id = id_tv.getText().toString();

            Data data = new Data(id, year_i, month_i, day_i, emoji_str, title, content, path);

            db_helper.save(data);
            Toast.makeText(getContext(), "저장이 됐습니다.", Toast.LENGTH_SHORT).show();


        });
    }




    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        assert intent != null;
                        uri = intent.getData();
                        path = uri.toString();
                        uri = Uri.parse(path);
                        Bitmap bitmap;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(v.getContext().getContentResolver(), uri);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ImageView imageView = v.findViewById(R.id.edit_imageView);
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
    );
}