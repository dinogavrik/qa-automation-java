package com.tcs.edu.service;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enumeration.Severity;
import com.tcs.edu.repository.MessageRepository;

import java.util.Collection;
import java.util.UUID;


/**
 * The class responsible for the final assembly of the message
 */
public class OrderedDistinctedMessageService extends ValidatedService implements MessageService {

    private final MessageRepository messageRepository;

    public OrderedDistinctedMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public UUID create(Message message) {
        return messageRepository.create(message);
    }

    @Override
    public Message getById(UUID key) {
        return messageRepository.findByPrimaryKey(key);
    }

    @Override
    public Collection<Message> get() {
        return messageRepository.findAll();
    }

    public Collection<Message> get(Severity severity) {
        return messageRepository.findBySeverity(severity);
    }

    public Collection<Message> get(String body) {
        return messageRepository.findByBody(body);
    }

    public Collection<Message> get(Severity severity, String body) {
        try {
            super.isArgsValid(severity, body);
        } catch (IllegalArgumentException e) {
            throw new ProcessException("Перадан невалидный параметр", e);
        }

        return messageRepository.findBySeverityAndBody(severity, body);
    }

    @Override
    public Message put(Message message) {
        return messageRepository.update(message);
    }

    @Override
    public Message delete(UUID id) {
        return messageRepository.delete(id);
    }

//    private static final MessageOrder DEFAULT_ORDER = MessageOrder.ASC;
//    private static final Doubling DEFAULT_DOUBLING = Doubling.DOUBLES;
//
//    private final Printer printer;
//    private final MessageDecorator messageDecorator;
//    private MessageRepository storage = new HashMapMessageRepository();
//
//    public OrderedDistinctedMessageService(MessageDecorator messageDecorator, Printer printer) {
//        this.messageDecorator = messageDecorator;
//        this.printer = printer;
//    }
//
//    public MessageRepository getStorage() {
//        return storage;
//    }
//
//    /**
//     * The method responsible for the process
//     * of assembling messages without additional
//     * parameters
//     *
//     * @param message  transmitted message
//     * @param messages transmitted messages
//     * @see #process(MessageOrder, Message, Message...)
//     * @return
//     */
//    public void process(Message message, Message... messages) {
//        process(DEFAULT_ORDER, message, messages);
//    }
//
//    /**
//     * Message assembly process method
//     * depending on the sort passed
//     *
//     * @param order    sort
//     * @param message  transmitted message
//     * @param messages transmitted messages
//     * @see #process(MessageOrder, Doubling, Message, Message...)
//     */
//    public void process(MessageOrder order, Message message, Message... messages) {
//        process(order, DEFAULT_DOUBLING, message, messages);
//    }
//
//    /**
//     * Message assembly process method depending on
//     * the passed sort and duplication parameter
//     *
//     * @param order    sort
//     * @param doubling duplication parameter value
//     * @param message  transmitted message
//     * @param messages transmitted messages
//     */
//    public void process(MessageOrder order, Doubling doubling, Message message, Message... messages) {
//        try {
//            super.isArgsValid(order, doubling, message, messages);
//        } catch (IllegalArgumentException e) {
//            throw new ProcessException("Перадан невалидный параметр", e);
//        }
//
//        var orderMess = sortMessages(order, message, messages);
//
//        for (Message mess : searchDuplicates(orderMess, doubling)) {
//            if (mess != null && mess.getBody() != null) {
//                printer.print(
//                        messageDecorator.decorate(format("%s %s", mess.getBody(), mess.getSeverityLevel().getLevel()))
//                );
//            }
//        }
//    }
//
//    /**
//     * Method to sort the array of messages
//     *
//     * @param order    sort
//     * @param message  transmitted message
//     * @param messages transmitted messages
//     */
//    private static List<Message> sortMessages(MessageOrder order, Message message, Message... messages) {
//        if (messages == null) {
//            messages = new Message[0];
//        }
//
//        List<Message> allMess = new ArrayList<>(Arrays.asList(messages));
//        allMess.add(0, message);
//
//        if (order == ASC) {
//            return allMess;
//        }
//
//        Message[] sortMess = new Message[allMess.size()];
//        for (int i = allMess.size() - 1; i >= 0; i--) {
//            sortMess[i] = allMess.get(allMess.size() - 1 - i);
//        }
//
//        return Arrays.asList(sortMess);
//    }
//
//    /**
//     * Method for finding duplicates in an array
//     *
//     * @param allMess  all transmitted messages
//     * @param doubling duplication parameter value
//     * @return depending on the parameter - messages with or without duplicates
//     */
//    private static List<Message> searchDuplicates(List<Message> allMess, Doubling doubling) {
//        if (doubling == Doubling.DOUBLES) {
//            return allMess;
//        }
//        LinkedHashSet<Message> doublingMess = new LinkedHashSet<>(allMess);
//        return new ArrayList<>(doublingMess);
//    }
}


