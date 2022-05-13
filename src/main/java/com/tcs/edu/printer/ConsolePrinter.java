package com.tcs.edu.printer;

/**
 * The class contains a set of methods that output
 * information to the console
 *
 * @author Gavrikova Irina
 */
public class ConsolePrinter implements Printer {

    /**
     * The method prints the received
     * parameter to the console
     *
     * @param message parameter output to the console
     */
    public void print(String message) {
        System.out.println(message);
    }
}
