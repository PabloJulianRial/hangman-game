package org.example;


import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Word word = new Word();
        Input userInput = new Input();
        Drawing drawing = new Drawing();
        ArrayList<String> guessedLetters = new ArrayList<>();
        word.word = word.getWord("src/main/java/words.txt", userInput);
//        System.out.println(word.word);
        boolean isGameFinished = false;

        while (!isGameFinished) {
            word.letters.clear();
            word.getLetters();
            System.out.println("Enter a letter:");
            char inputChar = userInput.getChar();
            System.out.println(word.letters);
            userInput.addLetterToGuessedLetters(inputChar, word.word, guessedLetters);
            word.createHiddenWord(guessedLetters);
            System.out.println(guessedLetters + "<<<<<<<<<<<<<");
            drawing.drawHangman();

        }

    }
}