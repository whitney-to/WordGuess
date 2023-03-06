package com.github.zipcodewilmington;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int guessesLeft;
    private String[] letterGuessed;
    private String[] letterNotGuessed;
    private String wordToGuess;

    private ArrayList<String> wordsList;
    public Game(ArrayList<String> wordsList){
        this.wordsList = wordsList;
        getRandomWord();
    }

    public void play(){

    }

    public void getRandomWord(){
        int index = (int)(Math.random() * wordsList.size());
        wordToGuess = wordsList.get(index);
    }

    @Override
    public String toString(){
        return "";
    }

}
