package org.example;

import java.util.ArrayList;

public class Game {

    public void playGame() throws InterruptedException {

        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String PURPLE = "\u001B[35m";
        String CYAN = "\u001B[36m";

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

        while (!isGameFinished) {
            System.out.println();

            if (cluesLeft > 0) {
                System.out.println(PURPLE + "Enter '?' to get a hint (you will loose one life)");
            }

            System.out.println();
            System.out.println(CYAN + "Enter a letter: ");
            System.out.println();
            char inputChar = userInput.getChar();

            if (!Character.isLetter(inputChar) && inputChar != '?') {
                System.out.println(RED + "Please enter only letters, try again.");
            } else if (guessedLetters.contains(inputChar)) {
                System.out.println(RED + "You've already said that, try again.");
            } else if (missedLetters.contains(inputChar)) {
                System.out.println(RED + "You've already said that, try again.");
            } else if (inputChar == '?' && !clueGiven) {
                System.out.println("The hint is being prepared....hang in there..........");
                System.out.println();
                Thread.sleep(4000);
                System.out.print(PURPLE + "Here is your hint: ");
                System.out.println(YELLOW + word.clue + RESET);
                System.out.println();
                System.out.println(word.createHiddenWord(guessedLetters));
                System.out.println();
                System.out.println("Lives left = " + (6 - livesLost));

                livesLost++;
                cluesLeft--;
                clueGiven = true;

            } else if (inputChar == '?' && clueGiven) {
                System.out.println(BLUE + "You are allowed 1 clue per game, which was: " + word.clue + ", remember??");
                System.out.println(CYAN + "Enter a letter: ");

            } else {
                userInput.addLetterToGuessedLetters(inputChar, word.word, guessedLetters);
                userInput.addLetterToMissedLetters(inputChar, word.word, missedLetters);
                if (guessedLetters.contains(inputChar)) {
                    guessed = true;
                }
                if (!guessed && livesLost < 5) {
                    livesLost++;
                    System.out.println();
                    System.out.println(RED + "Wrong letter, try again." + RESET);
                    System.out.println();
                    System.out.println("Lives left = " + (6 - livesLost));
                    System.out.println(word.createHiddenWord(guessedLetters));
                } else if (!guessed && livesLost == 5) {
                    livesLost++;
                    System.out.println(RED + "Wrong letter, sorry, that was your last chance." + RESET);
                    System.out.println("Lives left = " + (6 - livesLost));
                    System.out.println(word.createHiddenWord(guessedLetters));
                } else {
                    System.out.print(BLUE + "Well done, you've guessed!, letter '");
                    System.out.print(YELLOW + String.valueOf(inputChar));
                    System.out.println(BLUE + "' has been revealed below." + RESET);
                    System.out.println(word.createHiddenWord(guessedLetters));
                    System.out.println();
                    System.out.println("Lives left = " + (6 - livesLost));
                    guessed = false;
                }
                drawing.drawHangman("src/main/java/Drawing" + livesLost + ".txt");
                if (livesLost >= 6) {
                    isGameFinished = true;
                    System.out.println("YOU ARE DEAD!!!!");
                    System.out.println();
                    System.out.print(BLUE + "The word was ");
                    System.out.println(YELLOW + word.word + RESET);
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

                } else if (guessedLetters.size() == word.word.length()) {
                    System.out.println("YOU HAVE WON!!!!");
                    System.out.println();
                    System.out.println("Press 'P' to play again");

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

