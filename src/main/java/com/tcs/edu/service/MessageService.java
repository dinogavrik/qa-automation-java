package com.tcs.edu.service;

import com.tcs.edu.decorator.PaginationDecorator;
import com.tcs.edu.enumeration.Severity;
import com.tcs.edu.enumeration.MessageOrder;

import java.util.*;

import static com.tcs.edu.decorator.TimestampMessageDecorator.decorate;
import static com.tcs.edu.enumeration.MessageOrder.DESC;
import static com.tcs.edu.printer.ConsolePrinter.print;

/**
 * The class responsible for the final assembly of the message
 */
public class MessageService {

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

    /**
     * Method for assembling a message with sorting
     *
     * @param level    message significance level
     * @param order    sort
     * @param message  transmitted message
     * @param messages transmitted messages
     */
    public static void process(Severity level, MessageOrder order, String message, String... messages) {
        for (String mess : sortMessages(order, message, messages)) {
            if (mess != null) {
                print(PaginationDecorator.decorate(String.format("%s %s", decorate(mess), level.getLevel())));
            }
        }
    }

    /**
     * Method to sort the array of messages
     *
     * @param order    sort
     * @param message  transmitted message
     * @param messages transmitted messages
     */
    private static List<String> sortMessages(MessageOrder order, String message, String... messages) {
        if (messages == null) {
            messages = new String[]{};
        }

        List<String> allMess = new ArrayList<>(Arrays.asList(messages));
        allMess.add(0, message);

        if (order == DESC) {
            String[] sortMess = new String[allMess.size()];
            for (int i = allMess.size() - 1; i >= 0; i--) {
                sortMess[i] = allMess.get(allMess.size() - 1 -i);
            }
            allMess = new ArrayList<>(Arrays.asList(sortMess));
        }

        return allMess;
    }
}

