package com.example.marmm.GeoGuessSwipe;

public class GeoObject {

    private String geoName;
    private int geoImageName;
    private Boolean europeYes;

    public GeoObject(String GeoName, int GeoImageName, boolean europeYes) {

        this.geoName = GeoName;
        this.geoImageName = GeoImageName;
        this.europeYes = europeYes;
    }

    public String getGeoName() {
        return geoName;
    }

    public void setGeoName(String geoName) {
        this.geoName = geoName;
    }

    public int getGeoImageName() {
        return geoImageName;
    }

    public void setGeoImageName(int geoImageName) {
        this.geoImageName = geoImageName;
    }

    public boolean getEuropeYes(){return europeYes;}

    public static final String[] PRE_DEFINED_GEO_OBJECT_NAMES = {
            "Denmark",
            "Canada",
            "Bangladesh",
            "Kazachstan",
            "Poland",
            "Malta",
            "Thailand",
    };


    public static final int[] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {
            R.drawable.img1_yes_denmark,
            R.drawable.img2_no_canada,
            R.drawable.img3_no_bangladesh,
            R.drawable.img4_yes_kazachstan,
            R.drawable.img6_yes_poland,
            R.drawable.img7_yes_malta,
            R.drawable.img8_no_thailand,
    };

    public static final boolean[] PRE_FEFINDED_GEO_OBJECT_EUROPE_YES = {
         true,
         false,
         false,
         true,
         true,
         true,
         false,
    };
}