 package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * The class contains a set of methods that decorate
 * messages by adding the current time to the string.
 *
 * @author Gavrikova irina
 */
public class TimestampMessageDecorator {
    /**
     * Variable with the number of displayed messages
     */
    private static int messageCount = 0;

    /**
     * Number of messages displayed per page
     */
    private static final int PAGE_SIZE = 2;
    /**
     * Method for calculating the current date, outputting
     * the transmitted message, the received date and
     * the counter of displayed messages to the console with pagination
     *
     * Side effect on global messageCount
     *
     * @param message parameter output to the console
     * @return decorate message with current date, counter and with pagination
     */
    public static String decorate(String message) {
        messageCount++;
        String template = "%d %s %s";
        if (messageCount % PAGE_SIZE == 0) {
           template += "\n ----";
        }
        return String.format(template, messageCount, Instant.now(), message);
    }
}
