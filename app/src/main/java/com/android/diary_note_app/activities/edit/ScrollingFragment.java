package com.android.diary_note_app.activities.edit;

import static android.app.Activity.RESULT_OK;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.edit.listener.OnEmojiSelectedListener;
import com.android.diary_note_app.activities.edit.listener.OnSelectFontListener;
import com.android.diary_note_app.db_helper.DB_helper;
import com.android.diary_note_app.db_helper.Data;
import com.android.diary_note_app.editActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import java.io.IOException;
import java.util.Calendar;

public class ScrollingFragment extends Fragment implements OnEmojiSelectedListener , OnSelectFontListener, View.OnClickListener {

    View v;
    Uri uri;
    String path;
    int year_i, month_i, day_i;
    EditText title_et, content_et;
    TextView date_tv, id_tv;
    String title, content;
    CalendarDay today;
    ImageView imageView;
    String emoji_str;
    String currentFont;
    StyleSpinner styleSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_scrolling, container, false);

        refresh();
        setEmojiImg();
        if(getActivity() instanceof editActivity){
            editActivity editActivity = (editActivity) getActivity();
            editActivity.setOnclick(this);
        }

        styleSpinner = new StyleSpinner(v);
        styleSpinner.SetOnSelectFontListener(this);
        if(getArguments() != null){
            setBundle(getArguments());
        }

        return v;
    }


    private void setBundle(Bundle bundle){
        String id = bundle.getString("id");
        DB_helper dbHelper = new DB_helper(v.getContext());
        Cursor[] cursor = dbHelper.getData(id);
        Cursor cursor_diary = cursor[0];
        Cursor cursor_style = cursor[1];
        cursor_diary.moveToFirst();
        cursor_style.moveToFirst();

        int year = cursor_diary.getInt(1);
        int month = cursor_diary.getInt(2);
        int day = cursor_diary.getInt(3);
        String emoji = cursor_diary.getString(4);
        String title = cursor_diary.getString(5);
        String content = cursor_diary.getString(6);
        path = cursor_diary.getString(7);
        String font = null;
        try{
            font = cursor_style.getString(1);
            String size = cursor_style.getString(2);
            String color = cursor_style.getString(3);
        }catch(CursorIndexOutOfBoundsException e){
            e.getStackTrace();
        }




        String str = year + "년 " + month + "월 " + day + "일";

        emoji_str = emoji;
        id_tv.setText(id);
        System.err.println(str);
        date_tv.setText(str);
        title_et.setText(title);
        content_et.setText(content);
        styleSpinner.setFont(font);

        uri = Uri.parse(path);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(v.getContext().getContentResolver(), uri);
        } catch (IOException e) {
            System.err.println("파일 없음");
        }
        ImageView imageView = v.findViewById(R.id.edit_imageView);
        imageView.setImageBitmap(bitmap);
        selectEmoji(emoji_str);
        setFont(font);

        dbHelper.close();
    }

    private void selectEmoji(String emoji){
        int i;

        switch (emoji){
            case "happy":
                i = R.drawable.emoji_happy;
                break;
            case "ache":
                i = R.drawable.emoji_ache;
                break;
            case "angry":
                i = R.drawable.emoji_angry;
                break;
            default:
                i = 0;
                break;
        }
        if(i != 0){
            onEmojiSelected(i, emoji);
        }
    }
    private void refresh(){
        title_et = v.findViewById(R.id.edit_title);
        title_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_DATETIME_VARIATION_NORMAL);
        content_et = v.findViewById(R.id.edit_content);
        id_tv = v.findViewById(R.id.edit_id);
        today = CalendarDay.today();
        currentFont = "null";
        emoji_str = "happy";
        setTextView();
        setDate(today.getYear(), today.getMonth(), today.getDay());
        setBtn();

        ImageView image = v.findViewById(R.id.edit_imageView);
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                image.setImageDrawable(null);
                path = null;
                return true;
            }
        });

        content_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                content_et.invalidate();
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
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
        }, mYear, mMonth, mDay);


        date_tv.setOnClickListener(view -> datePickerDialog.show());
    }
    private void setDate(int year, int month, int day){
        year_i = year;
        month_i = month;
        day_i = day;
        String date = year + "년 " + month + "월 " + day + "일";
        date_tv.setText(date);
    }
    public void setBtn(){

        Button imageBtn = v.findViewById(R.id.edit_btn);

        imageBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            intent.setAction(Intent.ACTION_PICK);
            activityResultLauncher.launch(intent);
            Toast.makeText(getContext(), "파일을 불러옵니다", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onSelect(String str) {
        setFont(str);
    }
    private void setFont(String str){
        if(!str.equals("null")){
            currentFont = str;
            content_et.setText(content_et.getText());
            String filePath = "/system/fonts/" + str + ".ttf";
            Typeface typeface = Typeface.createFromFile(filePath);
            content_et.setTypeface(typeface);
            content_et.invalidate();


        }
    }

    @Override
    public void onClick(View view) {
        title = title_et.getText().toString();
        content = content_et.getText().toString();
        String id = id_tv.getText().toString();

        Data data = new Data(id, year_i, month_i, day_i, emoji_str, title, content, path, currentFont, null, null);

        DB_helper db_helper = new DB_helper(getContext());
        db_helper.save(data);
        db_helper.close();

        Toast.makeText(getContext(), "저장이 됐습니다.", Toast.LENGTH_SHORT).show();
    }
}