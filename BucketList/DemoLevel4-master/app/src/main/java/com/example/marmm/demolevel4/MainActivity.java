package com.example.marmm.demolevel4;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BucketAdapter.ReminderClickListener  {

    private List<Bucket> buckets;

    private MainViewModel mainViewModel;

    private BucketAdapter adapter;
    private RecyclerView recyclerView;

    public static final String EXTRA_REMINDER = "Bucket";
    public static final int REQUESTCODE = 1234;

    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buckets = new ArrayList<>();

        mainViewModel = new MainViewModel(getApplicationContext());

        mainViewModel.getBuckets().observe(this, new Observer<List<Bucket>>() {
            @Override
            public void onChanged(@Nullable List<Bucket> buckets) {
                MainActivity.this.buckets = buckets;
                updateUI();
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        updateUI();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the user text from the textfield
                //String text = mNewReminderText.getText().toString();
                //Bucket newReminder = new Bucket(text);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        int position = (viewHolder.getAdapterPosition());
                        mainViewModel.delete(buckets.get(position));
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckBoxChanged(int position, Boolean isChecked){
        Bucket bucket = buckets.get(position);
        bucket.setIsChecked(isChecked);
        buckets.set(position, bucket);
        mainViewModel.update(buckets.get(position));
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new BucketAdapter(buckets, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.swapList(buckets);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
                Bucket updatedBucket = data.getParcelableExtra(MainActivity.EXTRA_REMINDER);

                mainViewModel.update(updatedBucket);
                updateUI();
            }
        }
    }
}
