package com.example.logoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
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
        updateScoreText();
    }
    private void displayQuestion(Question question){
        ImageView logoDisplay = findViewById(R.id.logoDisplay);
        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button answer4 = findViewById(R.id.answer4);
        TextView titleView = findViewById(R.id.titleView);
        if(question.getLogoAnswer().equals("")){
            titleView.setText(" You've answered all Questions! ");
            logoDisplay.setVisibility(View.GONE);
            answer1.setVisibility(View.GONE);
            answer2.setVisibility(View.GONE);
            answer3.setVisibility(View.GONE);
            answer4.setVisibility(View.GONE);
            return;
        }
        logoDisplay.setImageResource(question.getLogoDrawableId());
        List<String> answersList = QuestionManager.instance.returnAnswersList(question);
        answer1.setText(answersList.get(0));
        answer2.setText(answersList.get(1));
        answer3.setText(answersList.get(2));
        answer4.setText(answersList.get(3));
        String titleText = String.format(" Question #%d/%d ",QuestionManager.instance.getQuestionNumber(),QuestionManager.instance.getTotalQuestions());
        titleView.setText(titleText);
    }
    private void onButtonClick(Button button){
        String answer = button.getText().toString();
        QuestionManager.instance.answerQuestion(answer);
        if(QuestionManager.instance.isAnswerCorrect(answer)){
            updateScoreText();
            animateToNextQuestion();
            animatePopupText(" CORRECT ",true);
        } else{
            animateToNextQuestion();
            animatePopupText(" WRONG ",false);
        }
    }
    private void bindButtons(){
        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button answer4 = findViewById(R.id.answer4);
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
    private void updateScoreText(){
        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText(String.format("Score: %d/%d",QuestionManager.instance.getCurrentScore(),QuestionManager.instance.getTotalQuestions()));
    }
    private void animateToNextQuestion(){
        animateTitleText();
        animateDisplayAndButtons();
    }
    public void animateTitleText() {
        TextView titleText = findViewById(R.id.titleView);
        Animator fadeOut = getFadeOutAnimator(titleText, 500, 0);
        Animator fadeIn = getFadeInAnimator(titleText, 500, 0);
        fadeOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fadeIn.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                fadeIn.start();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        fadeOut.start();
    }

    public void animatePopupText(String text,boolean isCorrect){
        TextView popupText = findViewById(R.id.popupText);
        AnimatorSet animatorSet = new AnimatorSet();
        Animator fadeIn = getFadeInAnimator(popupText, 500, 0);
        Animator fadeOut = getFadeOutAnimator(popupText, 500, 500);
        List<Animator> animators = new ArrayList<>();
        animators.add(fadeIn);
        animators.add(fadeOut);
        animatorSet.playSequentially(animators);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                popupText.setText(text);
                if (isCorrect) {
                    popupText.setTextColor(Color.GREEN);
                } else {
                    popupText.setTextColor(Color.RED);
                }
            }
            @Override
            public void onAnimationEnd(Animator animator) {
            }
            @Override
            public void onAnimationCancel(Animator animator) {
            }
            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animatorSet.start();
    }

    public void animateDisplayAndButtons() {
        ImageView logoDisplay = findViewById(R.id.logoDisplay);
        Animator fadeOut = getFadeOutAnimator(logoDisplay, 500, 0);
        Animator fadeIn = getFadeInAnimator(logoDisplay, 500, 0);
        fadeOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                QuestionManager.instance.randomizeQuestion();
                displayQuestion(QuestionManager.instance.getCurrentQuestion());
                fadeIn.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                QuestionManager.instance.randomizeQuestion();
                displayQuestion(QuestionManager.instance.getCurrentQuestion());
                fadeIn.start();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        fadeOut.start();
    }

    public static Animator getFadeInAnimator(View view, int duration, int delay){
        ObjectAnimator fadeAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1);
        fadeAnimator.setDuration(duration);
        fadeAnimator.setStartDelay(delay);
        return fadeAnimator;
    }

    public static Animator getFadeOutAnimator(View view, int duration, int delay){
        ObjectAnimator fadeAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1, 0);
        fadeAnimator.setDuration(duration);
        fadeAnimator.setStartDelay(delay);
        return fadeAnimator;
    }
}