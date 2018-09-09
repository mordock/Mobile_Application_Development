package com.example.maxwe.highorlow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView highscoreText;

    ListView listView;
    ImageView diceImage;

    Button lowButton;
    Button highButton;

    int hiddenNumber = 0;
    int numberOnScreen = 0;
    int score = 0;
    int highscore = 0;

    ArrayAdapter<String> arrayAdapter;
    public List<String> numberList;

    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        scoreText = findViewById(R.id.ScoreText);
        highscoreText = findViewById(R.id.HighScoreText);

        listView = findViewById(R.id.ListView);

        diceImage = findViewById(R.id.DiceImage);

        lowButton = findViewById(R.id.LowButton);
        highButton = findViewById(R.id.HighButton);

        numberList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, numberList);

        listView.setAdapter(arrayAdapter);

        random = new Random();
        UpdateNumbers();

        highButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hiddenNumber > numberOnScreen){
                    SetToList();
                    score++;
                    scoreText.setText("Score: " + score);
                    Toast.makeText(getApplicationContext(),String.valueOf(score) + " " + String.valueOf(hiddenNumber) + " " + String.valueOf(numberOnScreen), Toast.LENGTH_SHORT).show();
                    UpdateNumbers();
                }else{
                    if(score > highscore){
                        highscore = score;
                        highscoreText.setText("Highscore: " + highscore);
                    }
                    SetToList();
                    score = 0;
                    scoreText.setText("Score: " + score);
                    UpdateNumbers();
                }
            }
        });

        lowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hiddenNumber < numberOnScreen){
                    SetToList();
                    score++;
                    scoreText.setText("Score: " + score);
                    UpdateNumbers();
                }else{
                    if(score > highscore){
                        highscore = score;
                        highscoreText.setText("Highscore: " + highscore);
                    }
                    SetToList();
                    score = 0;
                    scoreText.setText("Score: " + score);
                    UpdateNumbers();
                }
            }
        });
    }

    public void UpdateNumbers(){
        hiddenNumber = random.nextInt(6) + 1;
        numberOnScreen = random.nextInt(6) + 1;
        if(hiddenNumber == numberOnScreen){
            UpdateNumbers();
        }
        if(numberOnScreen == 1){
            diceImage.setImageResource(R.drawable.d1);
        }else if(numberOnScreen == 2){
            diceImage.setImageResource(R.drawable.d2);
        }else if(numberOnScreen == 3){
            diceImage.setImageResource(R.drawable.d3);
        }else if(numberOnScreen == 4){
            diceImage.setImageResource(R.drawable.d4);
        }else if(numberOnScreen == 5){
            diceImage.setImageResource(R.drawable.d5);
        }else if(numberOnScreen == 6){
            diceImage.setImageResource(R.drawable.d6);
        }
    }

    public void SetToList(){
        numberList.add("Throw was "+ numberOnScreen);
        arrayAdapter.notifyDataSetChanged();
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
}
