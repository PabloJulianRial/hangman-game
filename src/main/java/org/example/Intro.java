package org.example;

public class Intro {

    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";

    public void printIntro() {
        System.out.println(GREEN + "*****************************************************************************");
        System.out.print(GREEN + "**************************");
        System.out.print(YELLOW + " THE HANGMAN ");
        System.out.println(GREEN + "**************************************");
        System.out.println(GREEN + "*****************************************************************************");
        System.out.print(GREEN + "***  ");
        System.out.print(YELLOW + "-Try to discover the hidden word by guessing it's letters.           ");
        System.out.println(GREEN + "***");
        System.out.print(GREEN + "***  ");
        System.out.print(YELLOW + "-Every time you miss, you loose a life, you have 6 lives.            ");
        System.out.println(GREEN + "***");
        System.out.print(GREEN + "***");
        System.out.print(YELLOW + "  -You can exchange a life for a hint of the word.                     ");
        System.out.println(GREEN + "***");
        System.out.println(GREEN + "*****************************************************************************");
        System.out.print(GREEN + "***************************");
        System.out.print(YELLOW + " GOOD LUCK! ");
        System.out.println(GREEN + "**************************************");
        System.out.println(GREEN + "*****************************************************************************");
    }


}
