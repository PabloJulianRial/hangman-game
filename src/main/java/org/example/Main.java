package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Word word = new Word();
        Input userInput = new Input();
        Drawing drawing = new Drawing();
        ArrayList<Character> guessedLetters = new ArrayList<>();
        ArrayList<Character> missedLetters = new ArrayList<>();
        word.word = word.getWord("src/main/java/words.txt", userInput);
        int livesLost = 0;
        boolean guessed = false;
        boolean isGameFinished = false;
        System.out.println("********************************************************");
        System.out.println("********************* THE HANGMAN **********************");
        System.out.println("********************************************************");
        System.out.println("-Try to discover the hidden word by guessing it's letters");
        System.out.println("-Every time you miss, you loose a life, you have 6 lives.");
        System.out.println("You can exchange a live for a clue of the word ");
        System.out.println("********************** GOOD LUCK! **********************");
        while (!isGameFinished) {
            System.out.println("Enter a letter:");
            char inputChar = userInput.getChar();
//            letterGuessed(inputChar, word.word, guessed);

            if (!Character.isLetter(inputChar)) {
                System.out.println("Please enter only letters, try again.");
            } else if (guessedLetters.contains(inputChar)) {
                System.out.println("You've already said that, try again. ");
            } else if (missedLetters.contains(inputChar)) {
                System.out.println("You've already said that, try again. ");
            } else {
                userInput.addLetterToGuessedLetters(inputChar, word.word, guessedLetters);
                userInput.addLetterToMissedLetters(inputChar, word.word, missedLetters);
                System.out.println(word.createHiddenWord(guessedLetters));
                System.out.println(guessedLetters + "<<<<<<<<<<<<<");
                if (guessedLetters.contains(inputChar)) {
                    guessed = true;
                }
                if (!guessed) {
                    livesLost++;
                    System.out.println("Wrong letter, try again");
                } else {
                    System.out.println("Well done, you've guessed");
                    guessed = false;
                }
                drawing.drawHangman("src/main/java/Drawing" + livesLost + ".txt");
                if (livesLost >= 6 || guessedLetters.size() == word.word.length()) {
                    isGameFinished = true;
                    System.out.println("YOU ARE DEAD!!!!");
                }
            }
        }
    }

//    public static void letterGuessed(char inputChar, String word, boolean guessed) {
//        inputChar = Character.toLowerCase(inputChar);
//        if (word.indexOf(inputChar) != -1) {
//            guessed = true;
//        } else {
//            guessed = false;
//        }
//    }
}
