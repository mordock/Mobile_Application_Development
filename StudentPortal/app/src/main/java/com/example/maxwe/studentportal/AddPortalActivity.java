package com.example.maxwe.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPortalActivity extends AppCompatActivity {
    Button button;
    EditText urlText;
    EditText titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addportal);

        urlText = findViewById(R.id.editText_URL);
        titleText = findViewById(R.id.editText_title);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlText.getText().toString();
                String title = titleText.getText().toString();

                Intent intent = new Intent(AddPortalActivity.this, MainActivity.class);
                Bundle extras = new Bundle();
                extras.putString("urlValue", url);
                extras.putString("titleValue", title);
                //intent.putExtra("urlValue", url);
                //intent.putExtra("titleValue", title);
                intent.putExtras(extras);
                MainActivity.startScreenFinish = true;
                startActivity(intent);
            }
        });
    }
}
