package com.github.zipcodewilmington;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author xt0fer
 * @version 1.0.0
 * @date 5/27/21 11:02 AM
 */
public class Hangman {
    static ArrayList<String> listOfWords = new ArrayList<>();
    public static void  getListOfWords(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("inputWords.txt"));
            while(scanner.hasNext()){
                String word = scanner.next();
                listOfWords.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not Found!");
        }
        if(scanner!=null) {scanner.close();}
    }

    public static void main(String[] args){

        // Greet user
        System.out.println("Let's Play Wordguess version 1.0\n");

        // Read in all words from input file
        getListOfWords();

        // Initialize a new Game;
        Game game = new Game(listOfWords);

        // Play game
        game.play();

        // Exit the program
        System.out.println("Good bye!");
    }
}
