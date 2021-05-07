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

    public void initialize() {
        questionList = new ArrayList<>();
        answeredQuestionsList = new ArrayList<>();
        random1 = new Random();
        addQuestion(new Question(R.drawable.nike,"Nike"));
        addQuestion(new Question(R.drawable.adidas,"Adidas"));
        addQuestion(new Question(R.drawable.pepsi,"Pepsi"));
        addQuestion(new Question(R.drawable.apple,"Apple"));
        addQuestion(new Question(R.drawable.amazon,"Amazon"));
        addQuestion(new Question(R.drawable.microsoft,"Microsoft"));
        addQuestion(new Question(R.drawable.playstation,"Playstation"));
        addQuestion(new Question(R.drawable.xbox,"Xbox"));
        addQuestion(new Question(R.drawable.gucci,"Gucci"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
//        addQuestion(new Question(R.drawable.nike,"Nike"));
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

    public boolean isAnswerCorrect(String answerText){
        return currentQuestion.getLogoAnswer().equals(answerText);
    }
}
