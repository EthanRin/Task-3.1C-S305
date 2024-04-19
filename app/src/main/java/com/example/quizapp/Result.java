package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    int rightAnswerCount;
    TextView congrats, resultVal;
    Button restart, endProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        congrats = findViewById(R.id.congrats_msg);
        resultVal = findViewById(R.id.result);
        String userName = getIntent().getStringExtra("key");
        rightAnswerCount = getIntent().getIntExtra("rightCount", 0);

        if (congrats != null && userName != null){
            congrats.setText("Congrats, " + userName + "!");
        }
        if (resultVal != null){
            resultVal.setText(Integer.toString(rightAnswerCount) + "/5");
        }

        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                intent.putExtra("key", userName);
                startActivity(intent);
            }
        });

        endProgram = findViewById(R.id.end);
        endProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); //Finish all the activities
                //Exit the app
                System.exit(0);
            }
        });
    }
}