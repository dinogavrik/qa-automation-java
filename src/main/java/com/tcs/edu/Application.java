package com.tcs.edu;

import com.tcs.edu.decorator.TimestampPaginationMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enumeration.Doubling;
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
                DESC,
                DISTINCT,
                new Message(REGULAR, "Hello0"),
                new Message(MAJOR, "Hello1"),
                new Message(MINOR, "Hello2"),
                new Message(MINOR, "Hello5"),
                new Message(MINOR, "Hello2"),
                new Message(REGULAR, "Hello2"),
                null,
                new Message(REGULAR, null),
                new Message(MINOR, "Hello5")

        );

        Message message1 = new Message(REGULAR, "Hello");
        Message message2 = new Message(REGULAR, "Hello");

        Message message3 = new Message(REGULAR, "Hello2");
        Message message4 = new Message(REGULAR, "Helloооооооо");

        System.out.println("Вывод объекта: " + new Message(REGULAR, "Hello"));
        System.out.println("Сравнение объектов (одинаковые сообщения): " + message1.equals(message2));
        System.out.println("Сравнение объектов (разные сообщения): " + message3.equals(message4));
        System.out.println("Хэш: " + message1.hashCode());
        System.out.println("Хэш: " + message2.hashCode());
        System.out.println("Хэш: " + message3.hashCode());
        System.out.println("Хэш: " + message4.hashCode());
        System.out.println(message4.hashCode() == message1.hashCode());
    }
}