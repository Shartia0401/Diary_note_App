package com.android.diary_note_app.activities.ui.recycler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.diary_note_app.MainActivity;
import com.android.diary_note_app.R;
import com.android.diary_note_app.db_helper.DB_helper;

import java.io.IOException;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{


    Context context;
    ArrayList<Item> list;

    int currentPosition;

    public RecyclerViewAdapter(Context context, ArrayList<Item> list) {
        super();
        this.context = context;
        this.list = list;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bitmap bitmap = setImg(list.get(position).image);
        currentPosition = holder.getAdapterPosition();
        list.get(position).setPosition(position);
        this.currentPosition = list.get(position).getPosition();
        String str = list.get(position).year + "년 "+ list.get(position).month + "월 "+list.get(position).day + "일";
        holder.id.setText(list.get(position).Id);
        holder.date.setText(str);
        holder.emoji.setText(list.get(position).emoji);
        holder.name.setText(list.get(position).name);
        holder.content.setText(list.get(position).content);
        holder.image.setImageBitmap(bitmap);

    }

    private Bitmap setImg(String path){

        Uri uri = Uri.parse(path);

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return bitmap;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recylcerview_row, parent, false);

        view.setOnClickListener(view1 -> new OpenEditAct(view1, 1));

        view.setOnLongClickListener(view12 -> {
            DB_helper db_helper = new DB_helper(view12.getContext());

            TextView tv = view12.findViewById(R.id.frag1_id);
            db_helper.delete(Integer.parseInt(tv.getText().toString()));
            parent.removeView(view12);
            System.err.println(currentPosition);
            list.remove(currentPosition);
            notifyItemRemoved(currentPosition);
            Toast.makeText(view12.getContext(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
            db_helper.close();

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
        TextView emoji;
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
    }
}
