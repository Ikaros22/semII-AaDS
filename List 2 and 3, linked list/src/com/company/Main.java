package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueExecution = true;

        IList<String> list = new OneWayLinkedList<String>();

        while (continueExecution) {
            String line = scanner.nextLine();
            System.out.println("!" + line);

            if (line.length() > 0) {
                String[] arguments = line.split(" ");

                switch (arguments[0].toLowerCase(Locale.ROOT)) {
                    case "end": continueExecution = false; break;
                    case "add": list.add(arguments[1]); break;
                    case "addat": list.addAt(Integer.parseInt(arguments[1]), arguments[2]); break;
                    case "clear": list.clear(); break;
                    case "contains": System.out.println(list.contains(arguments[1])); break;
                    case "get": System.out.println(list.get(Integer.parseInt(arguments[1]))); break;
                    case "set": list.set(Integer.parseInt(arguments[1]), arguments[2]); break;
                    case "indexof": System.out.println(list.indexOf(arguments[1])); break;
                    case "isempty": System.out.println(list.isEmpty()); break;
                    case "remove": System.out.println(list.remove(arguments[1])); break;
                    case "removeat": System.out.println(list.removeAt(Integer.parseInt(arguments[1]))); break;
                    case "size": System.out.println(list.size()); break;
                    case "print": list.print(); break;
                    default: System.out.println("Unknown command: " + arguments[0]);
                }
            }
        }

        System.out.println("End of execution.");
    }
}
