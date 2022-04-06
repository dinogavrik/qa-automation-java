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
     * Method for calculating the current date, outputting
     * the transmitted message, the received date and
     * the counter of displayed messages to the console
     *
     * Side effect on global messageCount
     *
     * @param message parameter output to the console
     * @return decorate message with current date and counter
     */
    public static String decorate(String message) {
        messageCount++;
        final var decoratedMessage = messageCount + " " + Instant.now() + " " + message;
        return decoratedMessage;
    }
}
