package com.example.marmm.animalviewpager;

import com.google.gson.annotations.SerializedName;

public class Recipe_Response {

    @SerializedName("recipe")
    private Recipy results;

    public Recipy getResults() {
        return results;
    }

    public void setResults(Recipy results) {
        this.results = results;
    }
}
