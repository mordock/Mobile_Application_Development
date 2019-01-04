package com.example.marmm.demolevel4;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BucketDao {

    @Query("SELECT * FROM bucket")
    public LiveData<List<Bucket>> getAllBuckets();

    @Insert
    public void insertReminders(Bucket bucket);

    @Delete
    public void deleteReminders(Bucket bucket);

    @Update
    public void updateReminders(Bucket bucket);


}
