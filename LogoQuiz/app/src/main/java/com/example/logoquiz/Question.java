package com.example.logoquiz;

public class Question {
    private int logoDrawableId;
    private String logoAnswer;

    public Question(int logoDrawableId, String logoAnswer) {
        this.logoDrawableId = logoDrawableId;
        this.logoAnswer = logoAnswer;
    }

    public int getLogoDrawableId() {
        return logoDrawableId;
    }

    public String getLogoAnswer() {
        return logoAnswer;
    }
}
