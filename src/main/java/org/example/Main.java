package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        Intro intro = new Intro();
        intro.printIntro();
        game.playGame();

    }
}






