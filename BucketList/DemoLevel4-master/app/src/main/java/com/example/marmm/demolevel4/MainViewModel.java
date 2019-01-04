package com.example.marmm.demolevel4;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class MainViewModel extends ViewModel {
    private BucketRepository repository;
    private LiveData<List<Bucket>> Buckets;
    public MainViewModel(Context context) {
        repository = new BucketRepository(context);
        Buckets = repository.getAllBuckets();
    }

    public LiveData<List<Bucket>> getBuckets() {
        return Buckets;
    }

    public void insert(Bucket bucket) {
        repository.insert(bucket);
    }

    public void update(Bucket bucket) {
        repository.update(bucket);
    }

    public void delete(Bucket bucket) {
        repository.delete(bucket);
    }
}
