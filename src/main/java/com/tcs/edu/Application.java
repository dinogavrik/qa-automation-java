package com.tcs.edu;

import com.tcs.edu.decorator.TimestampPaginationMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enumeration.Doubling;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;
import com.tcs.edu.service.ProcessException;

import static com.tcs.edu.enumeration.Doubling.DISTINCT;
import static com.tcs.edu.enumeration.Doubling.DOUBLES;
import static com.tcs.edu.enumeration.MessageOrder.DESC;
import static com.tcs.edu.enumeration.Severity.*;
import static java.lang.String.format;

class Application {
    public static void main(String[] args) {
//        service.process(
//                DESC,
//                DOUBLES,
//                new Message(REGULAR, "Hello0"),
//                new Message(MAJOR, "Hello1"),
//                new Message(MINOR, "Hello2"),
//                new Message(MINOR, "Hello5"),
//                new Message(MINOR, ""),
//                new Message(REGULAR, "Hello2"),
//                null,
//                new Message(REGULAR, null)
//        );
//
//        System.out.println("Вывод объекта: " + new Message(REGULAR, "Hello"));
//        System.out.println(
//                "Сравнение объектов (одинаковые сообщения): "
//                        + new Message(REGULAR, "Hello").equals(new Message(REGULAR, "Hello")));
//        System.out.println(
//                "Сравнение объектов (разные сообщения): "
//                        + new Message(REGULAR, "Hello2").equals(new Message(REGULAR, "Helloооооооо")));
//        System.out.println("Хэш: " + new Message(REGULAR, "Hello").hashCode());
//        System.out.println("Хэш: " + new Message(REGULAR, "Hello2").hashCode());
//        System.out.println("Хэш: " + new Message(REGULAR, "Helloооооооо").hashCode());
//        System.out.println(
//                new Message(REGULAR, "Helloооооооо").hashCode() == new Message(REGULAR, "Hello").hashCode());


        checkValidArgument();
        checkInvalidArgumentOrder();
        checkInvalidArgumentDoubling();
    }

    private static MessageService service = new OrderedDistinctedMessageService(
            new TimestampPaginationMessageDecorator(),
            new ConsolePrinter());

    public static void checkValidArgument() {
        exceptionAssert(
                ProcessException.class,
                () -> service.process(DESC, DISTINCT, new Message(MINOR, "Hello")));
    }

    public static void checkInvalidArgumentOrder() {
                exceptionAssert(
                        ProcessException.class,
                        () -> service.process(null, DISTINCT, new Message(MINOR, "Hello")));
    }

    public static void checkInvalidArgumentDoubling() {
        exceptionAssert(
                ProcessException.class,
                () -> service.process(DESC, (Doubling) null, new Message(MINOR, "Hello")));
    }


    public static <T extends Exception> T exceptionAssert(Class<T> exceptionClass, Runnable function) {
        T exception = null;
        boolean isExp = true;
        try {
            function.run();
        } catch (Exception e) {
            if (exceptionClass.isInstance(e)) {
                System.out.println(format("%s %s %s %s",
                        "Проверка в ",
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        "выполнена успешно, было выбрашено ожидаемое исключение",
                        exceptionClass.getName()));
                exception = (T) e;
            } else {
                System.out.println(format("%s %s %s %s",
                        "Проверка ",
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        "выполнена неуспешно. Ожиидаемое исключение не найдено. Было выброшено другое исключение:",
                        e.getClass().getName()));
            }
            isExp = false;
        }

        if (isExp) System.out.println("Проверка в "
                + Thread.currentThread().getStackTrace()[2].getMethodName()
                + "выполнена неуспешно. Эксепшн не был выброшен");

        return exception;

    }

}


