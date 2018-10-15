package com.example.maxwe.level4gamebacklog;

import android.content.Intent;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleAdapter.GameClickListener {

    private TextView titleView;
    private TextView platformView;
    private TextView statusView;
    private TextView dateView;

    private RecyclerView recyclerView;
    private RecycleAdapter adapter;

    private FloatingActionButton fab;

    List<Game> games;

    static AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        titleView = findViewById(R.id.TitleView);
        platformView = findViewById(R.id.PlatformView);
        statusView = findViewById(R.id.Status);
        dateView = findViewById(R.id.DateView);

        games = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecycleAdapter(games, this);
        recyclerView.setAdapter(adapter);

        db = AppDatabase.getInstance(this);

        games = db.gameDao().getAllGames();

        adapter.notifyDataSetChanged();

        adapter.setData(games);

        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateGame.class);
                startActivity(intent);
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                        int pos = (viewHolder.getAdapterPosition());
                        db.gameDao().deleteGames(games.get(pos));
                        games = db.gameDao().getAllGames();
                        adapter.setData(games);
                        adapter.notifyDataSetChanged();
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    public static boolean isUpdating = false;
    public void gamesOnClick(int i){
        Intent intent = new Intent(MainActivity.this, UpdateGame.class);
        Game gameToUpdate = games.get(i);
        String title = gameToUpdate.getTitle().toString();
        String platform = gameToUpdate.getPlatform().toString();
        String status = gameToUpdate.getStatus().toString();
        String notes = gameToUpdate.getNotes().toString();
        Long id = gameToUpdate.getId();

        Bundle extras = new Bundle();

        extras.putString("title", title);
        extras.putString("platform", platform);
        extras.putString("status", status);
        extras.putString("notes", notes);
        extras.putLong("id", id);

        intent.putExtras(extras);
        isUpdating = true;
        startActivity(intent);
    }
}
