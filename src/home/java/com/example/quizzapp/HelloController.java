package com.example.quizzapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label ans1;
    @FXML
    private Label ans2;
    @FXML
    private Label ans3;
    @FXML
    private Label ans4;
    @FXML
    private TextField answer;
    @FXML
    protected void onHelloButtonClick() throws IOException {
        int i=0;
        boolean continuer = true;
        List<String> Questions = readQuestions("C:\\Users\\user\\IdeaProjects\\quizzapp\\src\\home\\java\\com\\example\\quizzapp\\questions.txt");
        List<String> Reponces = readCorrectAnswers("C:\\Users\\user\\IdeaProjects\\quizzapp\\src\\home\\java\\com\\example\\quizzapp\\reponces.txt");
        List<List<String>> Fausses = readWrongAnswers("C:\\Users\\user\\IdeaProjects\\quizzapp\\src\\home\\java\\com\\example\\quizzapp\\fausse.txt");

        do{
            welcomeText.setText(Questions.get(i));
            Fausses.get(i).add(Reponces.get(i));
            Collections.shuffle(Fausses.get(i));
            ans1.setText(Fausses.get(i).get(0));
            ans2.setText(Fausses.get(i).get(1));
            ans3.setText(Fausses.get(i).get(2));
            ans4.setText(Fausses.get(i).get(3));

            String answerE = answer.getText();
            if(answerE.equals(Reponces.get(i)) || answerE.equals("")){
                continuer = true;
                i++;
            }
        }while ( (continuer == true));

        welcomeText.setText("Perdu");
        ans1.setText("");
        ans2.setText("");
        ans3.setText("");
        ans4.setText("");

    }

    public List<String> readQuestions(String filename) throws IOException {
        List<String> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                questions.add(line);
            }
        }
        return questions;
    }

    // Method to read correct answers from reponses.txt
    public List<String> readCorrectAnswers(String filename) throws IOException {
        List<String> correctAnswers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                correctAnswers.add(line);
            }
        }
        return correctAnswers;
    }

    // Method to read wrong answers from fausses.txt
    public List<List<String>> readWrongAnswers(String filename) throws IOException {
        List<List<String>> wrongAnswersList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wrongAnswers = line.split(",");
                List<String> wrongAnswersForQuestion = new ArrayList<>();
                for (String answer : wrongAnswers) {
                    wrongAnswersForQuestion.add(answer.trim());
                }
                wrongAnswersList.add(wrongAnswersForQuestion);
            }
        }
        return wrongAnswersList;
    }
}