package com.example.maxwe.level4gamebacklog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.maxwe.level4gamebacklog.MainActivity.db;

public class UpdateGame extends AppCompatActivity {

    private EditText titleText;
    private EditText platformText;
    private EditText notesText;

    private FloatingActionButton fab;

    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_updategame);

        titleText = findViewById(R.id.editText_title);
        platformText = findViewById(R.id.editText_platform);
        notesText = findViewById(R.id.editText_notes);

        fab = findViewById(R.id.floatingActionButton2);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.isUpdating){
                    Game newGame = db.gameDao().getGame(id);
                    newGame.setTitle(titleText.getText().toString());
                    newGame.setPlatform(platformText.getText().toString());
                    newGame.setNotes(notesText.getText().toString());

                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String date = df.format(c);
                    newGame.setDate(date);

                    newGame.setStatus(spinner.getSelectedItem().toString());

                    db.gameDao().updateGames(newGame);

                    Intent intent = new Intent(UpdateGame.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    String title = titleText.getText().toString();
                    String platform = platformText.getText().toString();
                    String notes = notesText.getText().toString();
                    String status = spinner.getSelectedItem().toString();
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String date = df.format(c);

                    Game newGame = new Game(title, platform, status, notes, date);

                    db.gameDao().insertGames(newGame);

                    Intent intent = new Intent(UpdateGame.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        if(MainActivity.isUpdating){
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();

            String title = extras.getString("title");
            String platform = extras.getString("platform");
            String status = extras.getString("status");
            String notes = extras.getString("notes");
            id = extras.getLong("id");

            titleText.setText(title);
            platformText.setText(platform);
            notesText.setText(notes);
            int spinnerPos = adapter.getPosition(status);
            spinner.setSelection(spinnerPos);
        }
    }
}
