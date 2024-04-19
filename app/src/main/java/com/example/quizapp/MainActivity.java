package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.inputName);
        String oldName = getIntent().getStringExtra("key");

        if (oldName != null && name != null){
            name.setText(oldName);
        }

        startButton = findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                if (userName.isEmpty()){
                    Toast.makeText(MainActivity.this, "You need to input your name before starting!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Question1.class);
                    intent.putExtra("key", userName);
                    startActivity(intent);
                }
            }
        });
    }
}