package com.example.insta.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insta.R;
import com.example.insta.view.GalleryFragment;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<Integer> list;

    public Adapter(List<Integer> list, GalleryFragment fragment) {
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        int res = (int) list.get(position);
        holder.image.setImageResource(res);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int height = ThreadLocalRandom.current().nextInt(250, 600);
            image.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        }
    }
}



