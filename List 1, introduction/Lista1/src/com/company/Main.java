
package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean continueExecution = true;

    while (continueExecution) {
        String line = scanner.nextLine();
        System.out.println("!" + line);

        if (line.length() > 0) {
            String[] arguments = line.split(" ");

            if(Integer.parseInt(arguments[1])<=0){
                arguments[0]="end";
                System.out.print("fail\n");
                break;
            }

            switch (arguments[0].toLowerCase(Locale.ROOT)) {
                case "end": continueExecution = false; break;
                case "triangle": Drawer.drawTriangle(Integer.parseInt(arguments[1])); break;
                case "square": Drawer.drawSquare(Integer.parseInt(arguments[1])); break;
                case "pyramid": Drawer.drawPyramid(Integer.parseInt(arguments[1])); break;
                case "christmastree": Drawer.drawChristmasTree(Integer.parseInt(arguments[1])); break;
                case "rectangle": Drawer.drawRectangle(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2])); break;
                default: System.out.println("Unknown command: " + arguments[0]);
            }
        }
    }

    System.out.println("End of execution.");
}
}

