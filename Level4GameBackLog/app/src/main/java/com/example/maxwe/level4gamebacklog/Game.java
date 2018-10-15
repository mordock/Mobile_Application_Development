package com.example.maxwe.level4gamebacklog;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "game")
public class Game {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "titleText")
    private String title;
    @ColumnInfo(name = "platformText")
    private String platform;
    @ColumnInfo(name = "statusText")
    private String status;
    @ColumnInfo(name = "notesText")
    private String notes;
    @ColumnInfo(name = "dataText")
    private String date;

    public Game(String title, String platform, String status, String notes, String date) {
        this.title = title;
        this.platform = platform;
        this.status = status;
        this.notes = notes;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
