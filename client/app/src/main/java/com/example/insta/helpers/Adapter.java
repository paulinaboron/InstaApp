package com.example.insta.helpers;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insta.R;
import com.example.insta.model.Photo;
import com.example.insta.view.GalleryFragment;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<Photo> photos;
    private String TAG = "xxx";

    public Adapter(List<Photo> photos) {
        this.photos = photos;
        Log.d(TAG, "Adapter: constructor");
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: adapter 2");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: adapter 3");
        Photo photo = photos.get(position);

//        todo
        Glide.with(holder.image.getContext())
                .load(Utils.adres+"/api/photos/getfile/" + photo.getId())
                .into(holder.image);

        Log.d(TAG, "onBindViewHolder: glide");
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + photos.size());
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int height = ThreadLocalRandom.current().nextInt(250, 600);
            image.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
            
            image.setOnClickListener(v->{
                Log.d(TAG, "ViewHolder: click");
            });
            
        }
    }
}



