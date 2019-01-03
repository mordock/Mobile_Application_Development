package com.example.marmm.animalviewpager;

import android.graphics.drawable.Drawable;

public class Animal {

        public int id;
        public String imageUrl;

    public Animal(int id, String imageRes) {
        this.id = id;
        this.imageUrl = imageRes;
    }

    public int getId() {
        return id;
    }

    public String getImageRes() {
        return imageUrl;
    }
}
