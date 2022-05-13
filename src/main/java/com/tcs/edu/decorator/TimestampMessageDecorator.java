package com.tcs.edu.decorator;

import java.time.Instant;

import static java.lang.String.format;

 /**
 * Class for calculating the current date and decorating the message with it
 *
 * @author Gavrikova irina
 */
public class TimestampMessageDecorator implements MessageDecorator {
    /**
     * Method for decorating a message with the current date
     *
     * @param message transmitted message
     * @return decorate message with current date
     */
    public String decorate(String message) {
        return format("%s %s", Instant.now(), message);
    }
}
