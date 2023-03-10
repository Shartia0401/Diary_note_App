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

import java.io.IOException;
import java.util.ArrayList;

public class frag4_RecyclerViewAdapter extends RecyclerView.Adapter<frag4_RecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<Item> list;

    public frag4_RecyclerViewAdapter(Context context, ArrayList<Item> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bitmap bitmap = setImg(list.get(position).image);
        holder.Id.setText(list.get(position).Id);
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
        View view = LayoutInflater.from(context).inflate(R.layout.recylcerview_row_frag4, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView a = view.findViewById(R.id.frag4_IdTextView);
                String id = a.getText().toString();
                new OpenEditAct(view, id);
                Toast.makeText(view.getContext(), "id : " + a.getText()  , Toast.LENGTH_SHORT).show();
            }
        });
        ImageView image = view.findViewById(R.id.frag4_image);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView Id;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.frag4_image);
            Id = itemView.findViewById(R.id.frag4_IdTextView);
        }
    }
}
