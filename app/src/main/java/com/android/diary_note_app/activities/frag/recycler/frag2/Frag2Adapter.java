package com.android.diary_note_app.activities.frag.recycler.frag2;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.diary_note_app.R;

import java.io.IOException;
import java.util.ArrayList;

public class Frag2Adapter extends RecyclerView.Adapter<Frag2Adapter.ViewHolder> {

    private ArrayList<DayDiaryItem> DiaryList;
    private Context context;
    public Frag2Adapter(Context context){
        super();
        this.context = context;
    }

    @NonNull
    @Override
    public Frag2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag2_item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Frag2Adapter.ViewHolder holder, int position) {
        holder.onBind(DiaryList.get(position), context);
    }

    public void setDiaryList(ArrayList<DayDiaryItem> list){
        this.DiaryList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        try{
            if(DiaryList.size() != 0){
                return DiaryList.size();
            }else{
                return 0;
            }
        }catch (NullPointerException e){
            return 0;
        }

    }

    public static class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView content;
        TextView id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.frag2_id);
            imageView = itemView.findViewById(R.id.frag2_image);
            title = itemView.findViewById(R.id.frag2_title);
            content = itemView.findViewById(R.id.frag2_content);
        }

        void onBind(DayDiaryItem item, Context context){

            id.setText(item.getId());
            imageView.setImageBitmap(setImg(item.imagePath, context));
            title.setText(item.title);
            content.setText(item.content);
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
