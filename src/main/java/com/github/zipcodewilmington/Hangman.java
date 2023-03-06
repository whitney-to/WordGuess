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
        try {
            Scanner scanner = new Scanner(new File("inputWords.txt"));
            while(scanner.hasNext()){
                String word = scanner.next();
                listOfWords.add(word);
            }
            System.out.println(listOfWords.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not Found!");
        }
    }

    public static void main(String[] args){
        getListOfWords();
        System.out.println("hi");
    }
}
