package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";


    // Method to print colored text
    public static void printColor(String text, String color) {
        System.out.print(color + text + RESET);
    }

    public static void main(String[] args)
            throws FileNotFoundException {
        Word word = new Word();
        Input userInput = new Input();
        Drawing drawing = new Drawing();
        ArrayList<Character> guessedLetters = new ArrayList<>();
        ArrayList<Character> missedLetters = new ArrayList<>();
        int randomIndex = (int) (Math.random() * 100);
        word.word = word.getWord(randomIndex, "src/main/java/words.txt", userInput);
        word.clue = word.getClue(randomIndex, "src/main/java/clues.txt", userInput);

        int livesLost = 0;
        boolean guessed = false;
        boolean isGameFinished = false;
        boolean clueGiven = false;
        int cluesLeft = 1;

        // Print colorful welcome message
        printColor("*****************************************************************************\n", GREEN);
        printColor("**************************", GREEN);
        printColor(" THE HANGMAN ", YELLOW);
        printColor("**************************************\n", GREEN);
        printColor("*****************************************************************************\n", GREEN);
        printColor("***  ", GREEN);
        printColor("-Try to discover the hidden word by guessing it's letters.     ", YELLOW);
        printColor("      ***\n", GREEN);
        printColor("***  ", GREEN);
        printColor("-Every time you miss, you loose a life, you have 6 lives.     ", YELLOW);
        printColor("       ***\n", GREEN);
        printColor("***", GREEN);
        printColor("  -You can exchange a live for a clue of the word.                     ", YELLOW);
        printColor("***\n", GREEN);
        printColor("*****************************************************************************\n", GREEN);
        printColor("***************************", GREEN);
        printColor(" GOOD LUCK! ", YELLOW);
        printColor("**************************************\n", GREEN);
        printColor("*****************************************************************************\n", GREEN);

        while (!isGameFinished) {
            // Input prompt
            System.out.println();

            if (cluesLeft > 0) {
                printColor("Press '?' to get a hint (you will loose one live)\n", PURPLE);
            }
            System.out.println();
            printColor("Enter a letter: \n", CYAN);
            System.out.println();
            char inputChar = userInput.getChar();
            if (!Character.isLetter(inputChar) && inputChar != '?') {
                printColor("Please enter only letters, try again.\n", RED);
            } else if (guessedLetters.contains(inputChar)) {
                printColor("You've already said that, try again.\n", RED);
            } else if (missedLetters.contains(inputChar)) {
                printColor("You've already said that, try again.\n", RED);
            } else if (inputChar == '?' && !clueGiven) {
                printColor("Here is your clue: ", PURPLE);

                printColor(word.clue + "\n", YELLOW);
                System.out.println();
                System.out.println("Lives left = " + (6 - livesLost));
                System.out.println();
                livesLost++;
                cluesLeft--;
                clueGiven = true;
            } else if (inputChar == '?' && clueGiven) {
                printColor("You are allowed 1 clue per game, which was: " + word.clue + ", remember??\n", BLUE);
                System.out.println("Enter a letter: ");

            } else {
                userInput.addLetterToGuessedLetters(inputChar, word.word, guessedLetters);
                userInput.addLetterToMissedLetters(inputChar, word.word, missedLetters);
//                System.out.println(word.createHiddenWord(guessedLetters));
                if (guessedLetters.contains(inputChar)) {
                    guessed = true;
                }
                if (!guessed && livesLost < 5) {
                    livesLost++;
                    System.out.println();
                    printColor("Wrong letter, try again.\n", RED);
                    System.out.println();
                    System.out.println("Lives left = " + (6 - livesLost));
                    System.out.println(word.createHiddenWord(guessedLetters));
                } else if (!guessed && livesLost == 5) {
                    livesLost++;
                    printColor("Wrong letter, sorry, that was your last chance.\n", RED);
                    System.out.println("Lives left = " + (6 - livesLost));
                    System.out.println(word.createHiddenWord(guessedLetters));
                } else {
                    printColor("Well done, you've guessed!, letter '", BLUE);
                    printColor(String.valueOf(inputChar), YELLOW);
                    printColor("' has been revealed below.\n", BLUE);
                    System.out.println(word.createHiddenWord(guessedLetters));
                    System.out.println();
                    System.out.println("Lives left = " + (6 - livesLost));
                    guessed = false;
                }
                drawing.drawHangman("src/main/java/Drawing" + livesLost + ".txt");
                if (livesLost >= 6 || guessedLetters.size() == word.word.length()) {
                    isGameFinished = true;
                    System.out.println("YOU ARE DEAD!!!!");
                    System.out.println();
                    printColor("The word was ", BLUE);
                    printColor(word.word + "\n", YELLOW);
                    System.out.println();
                    System.out.println("Press 'P' to play again");
                    inputChar = userInput.getChar();
                    if (inputChar == 'P') {
                        isGameFinished = false;
                        livesLost = 0;
                        clueGiven = false;
                        cluesLeft = 1;
                        guessedLetters.clear();
                        missedLetters.clear();
                        int nextRandomIndex = (int) (Math.random() * 100);
                        word.word = word.getWord(nextRandomIndex, "src/main/java/words.txt", userInput);
                        word.clue = word.getClue(nextRandomIndex, "src/main/java/clues.txt", userInput);
                    }

                }
            }
        }
    }

}
