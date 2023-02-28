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
import android.widget.DatePicker;
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
import com.android.diary_note_app.R;
import com.android.diary_note_app.db_helper.DB_helper;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import java.io.IOException;
import java.util.Calendar;

public class ScrollingFragment extends Fragment {

    View v;
    Uri uri;
    String path;

    EditText title_et;
    EditText content_et;
    TextView date_tv;
    String title;
    String content;
    CalendarDay today;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_scrolling, container, false);
        refresh();
        if(getArguments() != null){
            setBundle(getArguments());
        }
        return v;
    }

    private void setBundle(Bundle bundle){
        String date = bundle.getString("date");
        String title = bundle.getString("title");
        String content = bundle.getString("content");
        String path = bundle.getString("attachment");
        date_tv.setText(date);
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
        today = CalendarDay.today();
        setTextView();
        setDate(today.getYear(), today.getMonth(), today.getDay());
        setBtn();


    }

    private void setTextView(){
        date_tv = v.findViewById(R.id.edit_TV);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setDate(year, month, dayOfMonth);
            }
        }, mYear, mMonth, mDay);


        date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    private void setDate(int year, int month, int day){
        String str= year + "년 " + month+ "월 " + day + "일";
        date_tv.setText(str);
    }



    public void setBtn(){
        Button imageBtn = v.findViewById(R.id.edit_btn);
        Button saveBtn = v.findViewById(R.id.edit_savebtn);

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                intent.setAction(Intent.ACTION_PICK);
                activityResultLauncher.launch(intent);
                Toast.makeText(getContext(), "파일을 불러옵니다", Toast.LENGTH_SHORT).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB_helper db_helper = new DB_helper(getContext());
                title = title_et.getText().toString();
                content = content_et.getText().toString();

                db_helper.insert(date_tv.getText().toString(), title, null, content, path);
                Toast.makeText(getContext(), "저장이 됐습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        assert intent != null;
                        uri = intent.getData();
                        path = uri.toString();
                        uri = Uri.parse(path);
                        Bitmap bitmap = null;
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