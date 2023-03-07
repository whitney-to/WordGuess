package com.github.zipcodewilmington;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    public final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String availableLetters;
    Scanner scanner;
    private int guessesLeft;
    private String[] letterGuessed;
    private String[] lettersToGuessed;
    private boolean isWon;

    private ArrayList<String> wordsList;
    public Game(ArrayList<String> wordsList){
        this.wordsList = wordsList;
        initializeNewGame();
    }

    public void initializeNewGame(){
        availableLetters = ALPHABET;
        isWon = false;
        this.lettersToGuessed = getRandomWord();
        this.guessesLeft = lettersToGuessed.length;
        this.letterGuessed = setInitialDashes();
    }
    public String[] getRandomWord(){
        int index = (int)(Math.random() * wordsList.size());
        return wordsList.get(index).split("");
    }
    private String[] setInitialDashes(){
        String[] dashes = new String[lettersToGuessed.length];
        for(int i = 0; i < lettersToGuessed.length; i++){
            dashes[i] = "-";
        }
        return dashes;
    }
    public void play(){
        boolean isRoundFinished = false;
        boolean isPlayAgain = true;
        while(isPlayAgain){
            printStatus();
            //user start guessing until game is done
            while(!isRoundFinished){
                isRoundFinished = askUserInput();
                printStatus();
                if(isWon){
                    System.out.println("Congratulation! You have won!");
                }
            }

            //user lose if not guessing all letters
            if(!isWon){
                String s = Arrays.stream(lettersToGuessed).collect(Collectors.joining());
                System.out.format("You Lost! You ran out of guesses. Your word is %s \n",s.toUpperCase());
            }

            // loop until user input either Y or N
            boolean flag = true;
            while(flag) {
                //checking if user want to play again
                System.out.println("----------------------------------------------");
                System.out.print("Do you want to play again? ( Y | N ): ");
                scanner = new Scanner(System.in);
                String input = scanner.next();

                //if user want to stop
                if (input.equalsIgnoreCase("N")) {
                    isPlayAgain = false;
                    flag=false;
                } else if (input.equalsIgnoreCase("Y")) { // if user want to continue, re-initialize new word
                    isRoundFinished = false;
                    initializeNewGame();
                    flag=false;
                } else {
                    System.out.println("Invalid Input!");
                }
            }
        }
    }

    public boolean askUserInput(){
        // prompt user for an input
        System.out.print("Enter a single character: ");
        scanner = new Scanner(System.in);
        String input = scanner.next();

        // check if user input just 1 letter, loop until user does
        while(!input.matches("[A-Za-z]")){
            System.out.println("Invalid Input! ");
            System.out.print("Enter a single character: ");
            input = scanner.next();
        }
        return checkUserInput(input);
    }

    public boolean checkUserInput(String letter){
        letter = letter.toUpperCase();
        if(!availableLetters.contains(letter)){
            System.out.println("----------------------------------------------");
            System.out.println("You have guessed this letter before!");
            return false;
        } else {
            int count = 0;
            for(int i = 0; i < lettersToGuessed.length; i ++){
                if(lettersToGuessed[i].equalsIgnoreCase(letter)){
                    letterGuessed[i] = letter;
                    availableLetters = availableLetters.replace(letter,"");
                    count++;
                }
            }
            if(count==0){
                guessesLeft--;
                if(guessesLeft==0){
                    return true;
                }
            } else {
                if(checkWin()){
                    isWon = true;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkWin(){
        for(String s : letterGuessed){
            if(s.equalsIgnoreCase("-")){
                return false;
            }
        }
        return true;
    }
    public void printStatus(){
        StringBuilder sb = new StringBuilder("");
        for(String s : letterGuessed){
            sb.append(s);
        }
        System.out.println("----------------------------------------------");
        System.out.format("Current guesses: %s",sb.toString()+"\n");
        System.out.format("You have %d guess(es) left!\n",this.guessesLeft);
    }

}
