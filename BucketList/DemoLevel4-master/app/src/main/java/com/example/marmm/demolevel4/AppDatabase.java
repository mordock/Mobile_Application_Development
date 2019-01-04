package com.example.marmm.demolevel4;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Bucket.class}, version = 4)

public abstract class AppDatabase extends RoomDatabase {
    public abstract BucketDao bucketDoa();

    private final static String NAME_DATABASE = "bucket_db";

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {

        //safeguard if something went wrong
        context.deleteDatabase("bucket_db");

        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE)
                    .build();

        }


        return instance;

    }

}

