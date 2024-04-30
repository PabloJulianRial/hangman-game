package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    private final Scanner input = new Scanner(System.in);

    char getChar() {
        return Character.toUpperCase(input.next().charAt(0));
    }

    ArrayList<String> readWordsFromFile(String filename) {
        ArrayList<String> wordsList = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                wordsList.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return wordsList;
    }

    void addLetterToGuessedLetters(char input, String wordToGuess, ArrayList<Character> guessedLetters) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == input) {
                guessedLetters.add(input);
            }
        }
    }

    void addLetterToMissedLetters(char input, String wordToGuess, ArrayList<Character> missedLetters) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) != input) {
                missedLetters.add(input);
            }
        }
    }
}