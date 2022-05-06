package com.tcs.edu.service;

import com.tcs.edu.decorator.PaginationDecorator;
import com.tcs.edu.enumeration.Doubling;
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

    private static final MessageOrder DEFAULT_ORDER = MessageOrder.ASC;
    private static final Doubling DEFAULT_DOUBLING = Doubling.DOUBLES;

    /**
     * Method for assembling the output message
     * @see #process(Severity, MessageOrder, String, String...)
     *
     * @param level    message significance level
     * @param message  transmitted message
     * @param messages transmitted messages
     */
    public static void process(Severity level, String message, String... messages) {
        process(level, DEFAULT_ORDER, message, messages);
    }

    /**
     * Method for assembling a message with sorting
     * @see #process(Severity, MessageOrder, Doubling, String, String...)
     * 
     * @param level    message significance level
     * @param order    sort
     * @param message  transmitted message
     * @param messages transmitted messages
     */
    public static void process(Severity level, MessageOrder order, String message, String... messages) {
        process(level, order, DEFAULT_DOUBLING, message, messages);
    }

    /**
     * Method for assembling a sort message with doubling or not
     *
     * @param level    message significance level
     * @param order    sort
     * @param doubling duplication parameter value
     * @param message  transmitted message
     * @param messages transmitted messages
     */
    public static void process(
            Severity level, MessageOrder order, Doubling doubling, String message, String... messages) {

        var orderMess = sortMessages(order, message, messages);

        for (String mess : searchDuplicates(orderMess, doubling)) {
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
                sortMess[i] = allMess.get(allMess.size() - 1 - i);
            }
            allMess = Arrays.asList(sortMess);
        }

        return allMess;
    }

    /**
     * Method for finding duplicates in an array
     *
     * @param allMess  all transmitted messages
     * @param doubling duplication parameter value
     * @return depending on the parameter - messages with or without duplicates
     */
    private static List<String> searchDuplicates(List<String> allMess, Doubling doubling) {

        if (doubling == Doubling.DISTINCT) {
            String[] doublingMess = new String[allMess.size()];
            int k = 0;
            boolean isDoubling = false;
            for (int i = 0; i < allMess.size(); i++) {
                for (int j = 0; j < doublingMess.length; j++) {
                    if (Objects.equals(doublingMess[j], allMess.get(i))) {
                        isDoubling = true;
                        break;
                    }
                }
                if (!isDoubling) {
                    doublingMess[k] = allMess.get(i);
                    k++;
                }
                isDoubling = false;
            }
            allMess = Arrays.asList(doublingMess);
        }

        return allMess;
    }
}

