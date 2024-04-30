package org.example;

import java.util.ArrayList;

public class Word {
    String word;
    String clue;

    public String getWord(int randomIndex, String filename, Input input) {
        ArrayList<String> words = input.readWordsFromFile(filename);
        return words.get(randomIndex);
    }

    String getClue(int randomIndex, String filename, Input input) {
        ArrayList<String> clues = input.readWordsFromFile(filename);
        return clues.get(randomIndex);
    }

    char[] createHiddenWord(ArrayList<Character> guessedLetters) {
        char[] hiddenWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            hiddenWord[i] = '_';
        }
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            for (int j = 0; j < guessedLetters.size(); j++) {
                if (letter == guessedLetters.get(j)) {
                    hiddenWord[i] = letter;
                }
            }
        }
        return hiddenWord;
    }
}