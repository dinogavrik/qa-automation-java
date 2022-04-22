package com.tcs.edu.decorator;

import static java.lang.String.format;

/**
 * Class for calculating the current line number
 * and pagination and decorating the message with them
 *
 * @author Gavrikova Irina
 */
public class PaginationDecorator {
    /**
     * Variable with the number of displayed messages
     */
    private static int messageCount = 0;

    /**
     * Number of messages displayed per page
     */
    private static final int PAGE_SIZE = 2;

    /**
     *
     * Method for calculating timing and pagination numbers
     *
     * @param message transmitted message
     * @return decorated message
     */
    public static String decorate(String message) {
        messageCount++;
        String template = "%d %s";
        if (messageCount % PAGE_SIZE == 0) {
           template += "\n ----";
        }
        return format(template, messageCount, message);
    }


}
