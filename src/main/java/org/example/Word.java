package org.example;

import java.util.ArrayList;

public class Word {
    public ArrayList<String> words = new ArrayList<>();
    public ArrayList<String> clues = new ArrayList<>();
    public String word;
    public String clue;
    public ArrayList<String> letters = new ArrayList<>();


    public String getWord(int randomIndex, String filename, Input input) {
        words = input.readWordsFromFile(filename);
        return words.get(randomIndex);

    }

    public String getClue(int randomIndex, String filename, Input input) {
        clues = input.readWordsFromFile(filename);
        return clues.get(randomIndex);
    }


    public char[] createHiddenWord(ArrayList<Character> guessedLetters) {

        char[] hiddenWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            hiddenWord[i] = '_';
        }
        boolean letterFound = false;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            for (int j = 0; j < guessedLetters.size(); j++) {
                if (letter == guessedLetters.get(j)) {
                    hiddenWord[i] = letter;
                    letterFound = true;
                } else {
                    letterFound = false;
                }
            }
        }

        return hiddenWord;
    }
}