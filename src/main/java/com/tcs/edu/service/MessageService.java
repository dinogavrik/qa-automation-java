package com.tcs.edu.service;

import com.tcs.edu.decorator.PaginationDecorator;
import com.tcs.edu.decorator.Severity;

import java.util.*;

import static com.tcs.edu.decorator.TimestampMessageDecorator.decorate;
import static com.tcs.edu.printer.ConsolePrinter.print;

/**
 * The class responsible for the final assembly of the message
 */
public class MessageService {

    public static boolean doubling = true;

    /**
     * Method for assembling the output message
     *
     * @param level    message significance level
     * @param message  transmitted message
     * @param messages transmitted messages
     */
    public static void process(Severity level, String message, String... messages) {
        if (messages == null) {
            messages = new String[]{};
        }
        List<String> allMess = new ArrayList<>(Arrays.asList(messages));
        allMess.add(0, message);
        for (String mess : allMess) {
            if (mess != null) {
                print(PaginationDecorator.decorate(String.format("%s %s", decorate(mess), level.getLevel())));
            }
        }
    }

}

