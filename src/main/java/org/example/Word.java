package org.example;

import java.util.ArrayList;

public class Word {
    public ArrayList<String> words = new ArrayList<>();
    public String word;
    public ArrayList<String> letters = new ArrayList<>();
    public int livesLost = 0;

    public String getWord(String filename, Input input) {
        words = input.readWordsFromFile(filename);
        int randomIndex = (int) (Math.random() * words.size());
        return words.get(randomIndex);
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
        if (!letterFound) {
            System.out.println("Wrong tray again");
            livesLost++;
        } else {
            System.out.println("well done you've guessed");
        }
        return hiddenWord;
    }
}