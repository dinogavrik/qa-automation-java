package com.tcs.edu;

import com.tcs.edu.decorator.TimestampPaginationMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;

import static com.tcs.edu.enumeration.Doubling.DISTINCT;
import static com.tcs.edu.enumeration.Doubling.DOUBLES;
import static com.tcs.edu.enumeration.MessageOrder.ASC;
import static com.tcs.edu.enumeration.MessageOrder.DESC;
import static com.tcs.edu.enumeration.Severity.*;

class Application {
    public static void main(String[] args) {

        MessageService service = new OrderedDistinctedMessageService(
                new TimestampPaginationMessageDecorator(),
                new ConsolePrinter()
        );

        service.process(
                ASC,
                DISTINCT,
                new Message(MAJOR, "Hello world! 1"),
                new Message(MINOR, "Hello world! 2"),
                new Message(MINOR, "Hello world! 3"),
                new Message(REGULAR, "Hello world! 3"),
                new Message(REGULAR, "Hello world! 3"));

    }
}