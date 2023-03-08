package com.android.diary_note_app.activities.frag.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.diary_note_app.R;
import com.android.diary_note_app.activities.frag.recycler.listener.RecyclerDeleteListener;

import java.io.IOException;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    RecyclerDeleteListener listener;
    Context context;
    ArrayList<Item> list;

    int currentPosition;

    public void setRecyclerDeleteListener(RecyclerDeleteListener listener){
        this.listener = listener;
    }

    public RecyclerViewAdapter(Context context, ArrayList<Item> list) {
        super();
        this.context = context;
        this.list = list;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.onBind(list.get(position), context);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recylcerview_row, parent, false);

        view.setOnClickListener(view1 -> new OpenEditAct(view1, 1));

        view.setOnLongClickListener(view12 -> {
            TextView tv = view12.findViewById(R.id.frag1_id);

            if(listener != null){
                listener.onDelete(tv.getText().toString());
                Toast.makeText(view12.getContext(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView date;
        ImageView emoji;
        TextView name;
        TextView content;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.frag1_id);
            image = itemView.findViewById(R.id.recyclerview_row_image);
            date = itemView.findViewById(R.id.recyclerview_row_date);
            emoji = itemView.findViewById(R.id.recyclerview_row_emoji);
            name = itemView.findViewById(R.id.recyclerview_row_name);
            content = itemView.findViewById(R.id.recyclerview_row_content);
        }

        public void onBind(Item item, Context context){
            Bitmap bitmap = setImg(item.image, context);
            String str = item.year + "년 "+ item.month + "월 "+item.day + "일";
            setEmoji(item.emoji);
            id.setText(item.Id);
            date.setText(str);
            name.setText(item.name);
            content.setText(item.content);
            image.setImageBitmap(bitmap);
        }

        private void setEmoji(String str){
            int emojis;
            switch (str){
                case "happy":
                    emojis = R.drawable.emoji_happy;
                    break;
                case "ache":
                    emojis = R.drawable.emoji_ache;
                    break;
                case "angry":
                    emojis = R.drawable.emoji_angry;
                    break;
                default:
                    emojis = 0;
                    break;
            }

            if(emojis != 0){
                emoji.setImageResource(emojis);
            }

        }

        private Bitmap setImg(String path, Context context){

            Uri uri = Uri.parse(path);

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            } catch (IOException e) {
                e.getStackTrace();
            }
            return bitmap;
        }
    }
}
