package com.example.marmm.demolevel4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    EditText editText_text, editText_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainViewModel = new MainViewModel(getApplicationContext());

         editText_text = findViewById(R.id.editText_text);
         editText_title = findViewById(R.id.editText_title);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText_text.getText().toString();
                String title = editText_title.getText().toString();

                Bucket newBucket = new Bucket(false, text, title);

                mainViewModel.insert(newBucket);

                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
