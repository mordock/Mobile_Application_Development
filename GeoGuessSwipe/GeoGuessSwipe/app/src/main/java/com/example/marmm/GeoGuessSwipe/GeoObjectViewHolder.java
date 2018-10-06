package com.example.marmm.GeoGuessSwipe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GeoObjectViewHolder extends RecyclerView.ViewHolder {
        public ImageView geoImage;
        public TextView geoName;
        public View view;

        public GeoObjectViewHolder(View itemView) {
            super(itemView);
            geoImage =  itemView.findViewById(R.id.geoImageView);
            geoName = itemView.findViewById(R.id.geoTextView);
            view = itemView;
        }
    }


