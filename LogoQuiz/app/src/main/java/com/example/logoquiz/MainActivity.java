package com.example.logoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionManager.instance.initialize();
        QuestionManager.instance.randomizeQuestion();
        displayQuestion(QuestionManager.instance.getCurrentQuestion());
        bindButtons();
    }
    private void displayQuestion(Question question){
        if(question.getLogoAnswer().equals("")){
            return;
        }
        ImageView logoDisplay = findViewById(R.id.logoDisplay);
        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button answer4 = findViewById(R.id.answer4);
        List<String> answersList = QuestionManager.instance.returnAnswersList(question);
        logoDisplay.setImageResource(question.getLogoDrawableId());
        answer1.setText(answersList.get(0));
        answer2.setText(answersList.get(1));
        answer3.setText(answersList.get(2));
        answer4.setText(answersList.get(3));
    }
    private void onButtonClick(Button button){
        if(QuestionManager.instance.isAnswerCorrect(button.getText().toString())){
            QuestionManager.instance.randomizeQuestion();
            displayQuestion(QuestionManager.instance.getCurrentQuestion());
        }
    }
    private void bindButtons(){
        ImageView logoDisplay = findViewById(R.id.logoDisplay);
        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button answer4 = findViewById(R.id.answer4);
        logoDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionManager.instance.randomizeQuestion();
                displayQuestion(QuestionManager.instance.getCurrentQuestion());
            }
        });
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(answer1);
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(answer2);
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(answer3);
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(answer4);
            }
        });
    }
}