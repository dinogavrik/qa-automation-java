package com.tcs.edu.service;

import com.tcs.edu.decorator.PaginationDecorator;
import com.tcs.edu.decorator.Severity;

import static com.tcs.edu.decorator.SeverityLevelDecorator.mapSeverity;
import static com.tcs.edu.decorator.TimestampMessageDecorator.decorate;
import static com.tcs.edu.printer.ConsolePrinter.print;

/**
 * The class responsible for the final assembly of the message
 */
public class MessageService {

    /**
     * Method for assembling the output message
     *
     * @param level message significance level
     * @param message transmitted message
     */
    public static void process (Severity level, String message) {
        print(PaginationDecorator.decorate(decorate(message) + " " + mapSeverity(level)));
    }


}
