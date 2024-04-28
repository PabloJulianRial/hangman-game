package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Drawing {
    public void drawHangman() {
        ArrayList<String> drawingLines = new ArrayList<>();
        try {
            File file = new File("src/main/java/Drawing.txt");
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
            System.out.println(drawingLines.get(i));
        }

    }


}
