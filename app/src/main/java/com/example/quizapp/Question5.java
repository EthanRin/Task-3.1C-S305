package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Question5 extends AppCompatActivity {
    Button answer1, answer2, answer3, submitButton;
    String selectedAnswer = "";
    int submitButtonClickCount = 0;
    TextView welcome;
    ProgressBar progressBar;
    int rightAnswerCount;
    int progress;
    int totalQuestions = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        welcome = findViewById(R.id.welcome_msg);
        String userName = getIntent().getStringExtra("key");
        rightAnswerCount = getIntent().getIntExtra("rightCount", 0);
        progress = getIntent().getIntExtra("progress", 1);
        progressBar = findViewById(R.id.progressBar);
        progress += 100 / totalQuestions;
        progressBar.setProgress(progress);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        submitButton = findViewById(R.id.submit);

        if (welcome != null && userName != null){
            welcome.setText("Hi, " + userName);
        }

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = answer1.getText().toString();
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = answer2.getText().toString();
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = answer3.getText().toString();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonClickCount++;
                if (submitButtonClickCount == 1){
                    if (!selectedAnswer.isEmpty()) {
                        submitButton.setText("Next");
                        if (selectedAnswer.equals(answer1.getText().toString())){
                            // Set the background color to green for the correct answer
                            rightAnswerCount++;
                            answer1.setBackgroundResource(R.drawable.right_answer);
                        } else {
                            // Set the background color to red for the incorrect answer
                            if (selectedAnswer.equals(answer2.getText().toString())){
                                answer1.setBackgroundResource(R.drawable.right_answer);
                                answer2.setBackgroundResource(R.drawable.wrong_answer);
                            }
                            if (selectedAnswer.equals(answer3.getText().toString())){
                                answer1.setBackgroundResource(R.drawable.right_answer);
                                answer3.setBackgroundResource(R.drawable.wrong_answer);
                            }
                        }
                    } else {
                        // No answer selected
                        submitButtonClickCount--;
                        Toast.makeText(Question5.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (submitButtonClickCount == 2){
                    Intent intent = new Intent(Question5.this, Result.class);
                    intent.putExtra("key", userName);
                    intent.putExtra("progress", progress);
                    intent.putExtra("rightCount", rightAnswerCount);
                    startActivity(intent);
                }
            }
        });
    }
}