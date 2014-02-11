package com.vishnevsky.dbcource;

import com.vishnevsky.dbcource.util.Commands;
import com.vishnevsky.dbcource.util.Messages;

import java.util.Scanner;

public class Main {

    public static void main(String [] args) {

        ConsoleApp app = new ConsoleApp();
        app.initialize();

        Scanner scanner = new Scanner(System.in);

        print("Start");

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase(Commands.STOP)) {
                break;
            } else if (input.startsWith(Commands.CREATE)) {
                print( app.create(input) );
            } else if (input.startsWith(Commands.DELETE)) {
                print( app.delete(input) );
            } else if (input.startsWith(Commands.UPDATE)) {
                print( app.update(input) );
            } else if (input.startsWith(Commands.GET)) {
                print( app.get(input) );
            } else {
                print(Messages.UNKNOWN_COMMAND);
            }
        }

        print("Bye");
    }

    private static void print(String msg) {
        System.out.println(msg);
    }

}
