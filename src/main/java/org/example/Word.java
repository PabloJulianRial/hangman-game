package org.example;

import java.util.ArrayList;

public class Word {
    public ArrayList<String> words = new ArrayList<>();
    public String word;
    public ArrayList<String> letters = new ArrayList<>();
    public ArrayList<String> hiddenWord = new ArrayList<>();
    public ArrayList<String> guessedLetters = new ArrayList<>();

    public String getWord(String filename, Input input) {
        words = input.readWordsFromFile(filename);
        int randomIndex = (int) (Math.random() * words.size());
        return words.get(randomIndex);
    }

    public void getLetters() {
        for (int i = 0; i < word.length(); i++) {
            letters.add(String.valueOf(word.charAt(i)));
        }

    }

    public void createHiddenWord(ArrayList<String> guessedLetters) {
        char[] hiddenWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            for (int j = 0; j < guessedLetters.size(); j++) {
                if (letter == guessedLetters.get(j).charAt(0)) {
                    hiddenWord[i] = guessedLetters.get(j).charAt(0);
                    break;
                } else {
                    hiddenWord[i] = '_';
                }

            }
        }
        System.out.println(hiddenWord);
    }


}