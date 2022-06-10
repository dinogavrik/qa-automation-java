package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.service.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;

import java.util.Collection;
import java.util.UUID;

import static com.tcs.edu.enumeration.Severity.*;

class Application {
    public static void main(String[] args) {
//        MessageService service = new OrderedDistinctedMessageService();
//        UUID randomId = UUID.randomUUID();
//
//        UUID messageId  = service.create(new Message(MINOR, "hello1"));
//        service.create(new Message(REGULAR, "hello2"));
//        service.create(new Message(REGULAR, "hello1"));
//
//        Message messageById = service.getById(messageId);
//        System.out.println("Соообщение по переданному ID " + messageById);
//        System.out.println("__________");
//
//        Collection<Message> getAll = service.get();
//        System.out.println("Все сообщения: " + getAll);
//        System.out.println("__________");
//
//        Collection<Message> getBySeverity = service.get(REGULAR);
//        System.out.println("Сообщения, где severity = regular " + getBySeverity);
//        System.out.println("__________");
//
//        Collection<Message> getByBody= service.get("hello1");
//        System.out.println("Сообщения, где body = hello1 " + getByBody);
//        System.out.println("__________");
//
//        Collection<Message> getBySeverityAndBody= service.get(REGULAR, "hello2");
//        System.out.println("Сообщения, где body = hello2 и severity = Regular " + getBySeverityAndBody);
//        System.out.println("__________");
//
//        System.out.println("=============================================");
//        Message messageUpdate = new Message(REGULAR, "UpdateMessage");
//        messageUpdate.setId(messageId);
//        Message update =  service.put(messageUpdate);
//        System.out.println("[Positive] Это сообщение было изменено: " + update);
//        Message update2 =  service.put(new Message(MAJOR, "No"));
//        System.out.println("[Negative] Это сообщение было изменено: " + update2);
//
//        System.out.println("=============================================");
//        UUID idMess = service.create(new Message(MINOR, "Hello"));
//        Message deletedMessage = service.delete(idMess);
//        System.out.println("[Positive] Эта запись была удалена: " + deletedMessage);
//        Message deletedMessageNonexistentId = service.delete(randomId);
//        System.out.println("[Negative] Эта запись была удалена: " + deletedMessageNonexistentId);

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


//        checkValidArgument();
//        checkInvalidArgumentOrder();
//        checkInvalidArgumentDoubling();

    }

//    private static MessageService service = new OrderedDistinctedMessageService(
//            new TimestampPaginationMessageDecorator(),
//            new ConsolePrinter());
//
//    public static void checkValidArgument() {
//        exceptionAssert(
//                ProcessException.class,
//                () -> service.process(DESC, DISTINCT, new Message(MINOR, "Hello")));
//    }
//
//    public static void checkInvalidArgumentOrder() {
//                exceptionAssert(
//                        ProcessException.class,
//                        () -> service.process(null, DISTINCT, new Message(MINOR, "Hello")));
//    }
//
//    public static void checkInvalidArgumentDoubling() {
//        exceptionAssert(
//                ProcessException.class,
//                () -> service.process(DESC, (Doubling) null, new Message(MINOR, "Hello")));
//    }
//
//
//    public static <T extends Exception> T exceptionAssert(Class<T> exceptionClass, Runnable function) {
//        T exception = null;
//        boolean isExp = true;
//        try {
//            function.run();
//        } catch (Exception e) {
//            if (exceptionClass.isInstance(e)) {
//                System.out.println(format("%s %s %s %s",
//                        "Проверка в ",
//                        Thread.currentThread().getStackTrace()[2].getMethodName(),
//                        "выполнена успешно, было выбрашено ожидаемое исключение",
//                        exceptionClass.getName()));
//                exception = (T) e;
//            } else {
//                System.out.println(format("%s %s %s %s",
//                        "Проверка ",
//                        Thread.currentThread().getStackTrace()[2].getMethodName(),
//                        "выполнена неуспешно. Ожиидаемое исключение не найдено. Было выброшено другое исключение:",
//                        e.getClass().getName()));
//            }
//            isExp = false;
//        }
//
//        if (isExp) System.out.println("Проверка в "
//                + Thread.currentThread().getStackTrace()[2].getMethodName()
//                + "выполнена неуспешно. Эксепшн не был выброшен");
//
//        return exception;
//
//    }

}


