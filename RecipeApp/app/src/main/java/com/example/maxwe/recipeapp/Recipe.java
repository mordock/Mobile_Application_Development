package com.example.maxwe.recipeapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe implements Parcelable {
    @SerializedName("recipe_id")
    @Expose
    private String recipeId;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("title")
    @Expose
    private String title;

    public Recipe(String title, String imageUrl, String recipeId) {
        this.imageUrl = imageUrl;
        this.recipeId = recipeId;
        this.title = title;
    }

    public String getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.recipeId);
        dest.writeString(this.imageUrl);
        dest.writeString(this.title);
    }

    protected Recipe(Parcel in) { ;
        this.recipeId = in.readString();
        this.imageUrl = in.readString();
        this.title = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
