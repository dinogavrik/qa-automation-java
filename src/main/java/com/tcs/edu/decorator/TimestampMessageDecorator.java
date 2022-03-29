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
     * Method for calculating the current date and
     * outputting the transmitted message and the received date to the console
     *
     * @param message parameter output to the console
     * @return decorate message with current date
     */
    public static String decorate(String message) {
        return Instant.now() + " " + message;
    }
}
