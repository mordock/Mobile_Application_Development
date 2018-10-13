package com.example.maxwe.studentportal;

public class Sites {
    private String siteTitle;
    private String url;

    public String getSiteTitle(){return siteTitle;}
    public void setSiteTitle(String siteTitle){this.siteTitle = siteTitle;}

    public String getUrl(){return url;}
    public void setUrl(String url){this.url = url;}

    public Sites(String siteTitle, String url){
        this.siteTitle = siteTitle;
        this.url = url;
    }
}
