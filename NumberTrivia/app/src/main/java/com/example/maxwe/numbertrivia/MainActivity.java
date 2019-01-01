package com.example.maxwe.numbertrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecycleAdapter.NumberClickListener{
    final int highestNumber = 101;
    TextView textView;

    private TextView valueView;
    private TextView quoteView;

    private RecyclerView recyclerView;
    private RecycleAdapter adapter;

    List<Numbers> numbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        valueView = findViewById(R.id.number);
        quoteView = findViewById(R.id.quote);

        numbersList = new ArrayList<>();

        recyclerView = findViewById(R.id.Recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecycleAdapter(numbersList, this);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //textView = findViewById(R.id.textView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SetQuoteTextView(String quoteMessage, int random) {
        //textView.setText(quoteMessage);

        Numbers newNumber = new Numbers(random, "bob");
        newNumber.setNumberText(quoteMessage);
        newNumber.setNumberValue(random);
        numbersList.add(newNumber);

        adapter.notifyDataSetChanged();

        adapter.setData(numbersList);
    }


    private void requestData(){
        NumberApiService service = NumberApiService.retrofit.create(NumberApiService.class);
        final int random = new Random().nextInt(highestNumber);
        Call<TriviaItem> call = service.getRandomTrivia(random);
        call.enqueue(new Callback<TriviaItem>() {
            @Override
            public void onResponse(Call<TriviaItem> call, Response<TriviaItem> response) {
                TriviaItem triviaItem = response.body();
                SetQuoteTextView(triviaItem.getText(), random);
            }

            @Override
            public void onFailure(Call<TriviaItem> call, Throwable t) {
                Snackbar.make(findViewById(R.id.layout),"failure", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    public void numberOnClick(int i) {
        //nothing happens for now. Possible extension for later
    }
}
