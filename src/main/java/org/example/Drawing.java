package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Drawing {
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";

    public static void printColor(String text, String color) {
        System.out.print(color + text + RESET);
    }

    public void drawHangman(String txtFile) {
        ArrayList<String> drawingLines = new ArrayList<>();
        try {
            File file = new File(txtFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                drawingLines.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < drawingLines.size(); i++) {
            printColor(drawingLines.get(i) + "\n", YELLOW);
        }

    }


}
