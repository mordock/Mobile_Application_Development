package com.example.maxwe.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity implements RecycleAdapter.SitesClickListener{
    private List<Sites> sites;

    private RecycleAdapter adapter;
    private RecyclerView recyclerView;

    private String urlValue;
    private String titleValue;

    public static boolean startScreenFinish = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sites = new ArrayList<>();

        recyclerView = findViewById(R.id.RecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecycleAdapter(sites, this);
        recyclerView.setAdapter(adapter);

        if(startScreenFinish) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            titleValue = extras.getString("titleValue");
            urlValue = extras.getString("urlValue");

            Sites newSite = new Sites(titleValue, urlValue);

            sites.add(newSite);
            adapter.notifyDataSetChanged();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddPortalActivity.class);
                startActivity(intent);
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

    @Override
    public void sitesOnClick(int i){
        Intent intent = new Intent(MainActivity.this, WebViewClass.class);
        intent.putExtra("urlWebView", urlValue);
        startActivity(intent);
    }


}
