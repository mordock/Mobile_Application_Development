package com.example.marmm.demolevel4;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BucketRepository {
    private AppDatabase database;
    private BucketDao bucketDao;
    private LiveData<List<Bucket>> buckets;
    private Executor executor = Executors.newSingleThreadExecutor();

    public BucketRepository(Context context) {
        database = AppDatabase.getInstance(context);
        bucketDao = database.bucketDoa();
        buckets = bucketDao.getAllBuckets();
    }

    public LiveData<List<Bucket>> getAllBuckets() {
        return buckets;
    }

    public void insert(final Bucket bucket) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bucketDao.insertReminders(bucket);
            }
        });
    }

    public void update(final Bucket bucket) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bucketDao.updateReminders(bucket);
            }
        });
    }

    public void delete(final Bucket bucket) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bucketDao.deleteReminders(bucket);
            }
        });
    }
}
