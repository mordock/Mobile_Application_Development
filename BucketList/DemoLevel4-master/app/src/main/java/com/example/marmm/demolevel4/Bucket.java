package com.example.marmm.demolevel4;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "bucket")
public class Bucket {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "remindertext")
    private String bucketText;

    @ColumnInfo(name = "bucketTitle")
    private String bucketTitle;

    @ColumnInfo(name = "isChecked")
    private Boolean isChecked;

    public Bucket(Boolean isChecked, String bucketText, String bucketTitle) {
        this.bucketText = bucketText;
        this.bucketTitle = bucketTitle;
        this.isChecked = isChecked;
    }

    public String getBucketText() {return bucketText;}
    public void setBucketText(String mReminderText) {
        this.bucketText = mReminderText;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getBucketTitle(){return bucketTitle;}
    public void setBucketTitle(String bucketTitle){this.bucketTitle = bucketTitle;}

    public Boolean getIsChecked(){
        return isChecked;
    }
    public void setIsChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
}
