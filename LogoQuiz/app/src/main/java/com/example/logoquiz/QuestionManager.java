package com.example.logoquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionManager {
    public static final QuestionManager instance = new QuestionManager();

    private Question currentQuestion;
    private List<Question> questionList;
    private List<Integer> answeredQuestionsList;
    private Random random1;
    private int currentScore;

    public void initialize() {
        questionList = new ArrayList<>();
        answeredQuestionsList = new ArrayList<>();
        random1 = new Random();
        currentScore = 0;
        initializeQuestions();
    }

    public void initializeQuestions(){
        addQuestion(new Question(R.drawable.nike,"Nike"));
        addQuestion(new Question(R.drawable.adidas,"Adidas"));
        addQuestion(new Question(R.drawable.pepsi,"Pepsi"));
        addQuestion(new Question(R.drawable.apple,"Apple"));
        addQuestion(new Question(R.drawable.amazon,"Amazon"));
        addQuestion(new Question(R.drawable.microsoft,"Microsoft"));
        addQuestion(new Question(R.drawable.playstation,"Playstation"));
        addQuestion(new Question(R.drawable.xbox,"Xbox"));
        addQuestion(new Question(R.drawable.gucci,"Gucci"));
        addQuestion(new Question(R.drawable.ferrari,"Ferrari"));
        addQuestion(new Question(R.drawable.audi,"Audi"));
        addQuestion(new Question(R.drawable.tesla,"Tesla"));
        addQuestion(new Question(R.drawable.mercedes,"Mercedes"));
        addQuestion(new Question(R.drawable.mcdo,"McDonalds"));
        addQuestion(new Question(R.drawable.unilever,"Unilever"));
        addQuestion(new Question(R.drawable.nestle,"Nestle"));
        addQuestion(new Question(R.drawable.hnm,"H&M"));
        addQuestion(new Question(R.drawable.uniqlo,"Uniqlo"));
        addQuestion(new Question(R.drawable.lacoste,"Lacoste"));
        addQuestion(new Question(R.drawable.ck,"Calvin Klein"));
        addQuestion(new Question(R.drawable.lg,"LG"));
        addQuestion(new Question(R.drawable.foodpanda,"FoodPanda"));
        addQuestion(new Question(R.drawable.sanmig,"San Miguel"));
        addQuestion(new Question(R.drawable.surf,"Surf"));
        addQuestion(new Question(R.drawable.sswan,"Silver Swan"));
        addQuestion(new Question(R.drawable.knorr,"Knorr"));
        addQuestion(new Question(R.drawable.nissin,"Nissin"));
        addQuestion(new Question(R.drawable.delmonte,"Del Monte"));
        addQuestion(new Question(R.drawable.bench,"Bench"));
        addQuestion(new Question(R.drawable.penshoppe,"Penshoppe"));
    }

    public void addQuestion(Question question){
        questionList.add(question);
    }

    public void randomizeQuestion(){
        currentQuestion = getRandomQuestion();
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public int getQuestionNumber(){return answeredQuestionsList.size();}

    public int getTotalQuestions(){return questionList.size();}

    private Question getRandomQuestion() {
        if(answeredQuestionsList.size() == questionList.size()){
            return  new Question(0,"");
        }
        Integer randomIndex = random1.nextInt(questionList.size());
        while (answeredQuestionsList.contains(randomIndex)) {
            randomIndex = random1.nextInt(questionList.size());
        }
        answeredQuestionsList.add(randomIndex);
        return  questionList.get(randomIndex);
    }

    public void resetAnsweredQuestions(){
        currentScore = 0;
        answeredQuestionsList.clear();
    }

    public List<String> returnAnswersList(Question question){
        int numberOfElements = 4;
        List<String> finalAnswersList = new ArrayList<>();
        Random random2 = new Random();
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex;
            String randomAnswer;
            do {
                randomIndex = random2.nextInt(questionList.size());
                randomAnswer = questionList.get(randomIndex).getLogoAnswer();
            }while(randomAnswer.equals(question.getLogoAnswer()) || finalAnswersList.contains(randomAnswer));
            finalAnswersList.add(randomAnswer);
        }
        int randomIndex = random2.nextInt(finalAnswersList.size());
        finalAnswersList.set(randomIndex,question.getLogoAnswer());
        return finalAnswersList;
    }

    public void answerQuestion(String answerText){
        if(isAnswerCorrect(answerText)){
            currentScore++;
        }
    }

    public boolean isAnswerCorrect(String answerText){
        return currentQuestion.getLogoAnswer().equals(answerText);
    }

    public int getCurrentScore() {
        return currentScore;
    }
}
