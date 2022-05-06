package com.tcs.edu.service;

import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enumeration.Doubling;
import com.tcs.edu.enumeration.MessageOrder;
import com.tcs.edu.printer.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.tcs.edu.enumeration.MessageOrder.DESC;
import static java.lang.String.format;


/**
 * The class responsible for the final assembly of the message
 */
public class OrderedDistinctedMessageService implements MessageService {

    private static final MessageOrder DEFAULT_ORDER = MessageOrder.ASC;
    private static final Doubling DEFAULT_DOUBLING = Doubling.DOUBLES;

    private final Printer printer;
    private final MessageDecorator messageDecorator;

    public OrderedDistinctedMessageService(MessageDecorator messageDecorator, Printer printer) {
        this.messageDecorator = messageDecorator;
        this.printer = printer;
    }

    /**
     * The method responsible for the process
     * of assembling messages without additional
     * parameters
     *
     * @param message  transmitted message
     * @param messages transmitted messages
     * @see #process(MessageOrder, Message, Message...)
     */
    public void process(Message message, Message... messages) {
        process(DEFAULT_ORDER, message, messages);
    }

    /**
     * Message assembly process method
     * depending on the sort passed
     *
     * @param order    sort
     * @param message  transmitted message
     * @param messages transmitted messages
     * @see #process(MessageOrder, Doubling, Message, Message...)
     */
    public void process(MessageOrder order, Message message, Message... messages) {
        process(order, DEFAULT_DOUBLING, message, messages);
    }

    /**
     * Message assembly process method depending on
     * the passed sort and duplication parameter
     *
     * @param order    sort
     * @param doubling duplication parameter value
     * @param message  transmitted message
     * @param messages transmitted messages
     */
    public void process(MessageOrder order, Doubling doubling, Message message, Message... messages) {
        var orderMess = sortMessages(order, message, messages);

        for (Message mess : searchDuplicates(orderMess, doubling)) {
            if (mess != null) {
                printer.print(
                        messageDecorator.decorate(format("%s %s", mess.getBody(), mess.getSeverityLevel().getLevel()))
                );
            }
        }
    }

    /**
     * Method to sort the array of messages
     *
     * @param order    sort
     * @param message  transmitted message
     * @param messages transmitted messages
     */
    private static List<Message> sortMessages(MessageOrder order, Message message, Message... messages) {
        if (messages == null) {
            messages = new Message[0];
        }

        List<Message> allMess = new ArrayList<>(Arrays.asList(messages));
        allMess.add(0, message);

        if (order == DESC) {
            Message[] sortMess = new Message[allMess.size()];
            for (int i = allMess.size() - 1; i >= 0; i--) {
                sortMess[i] = allMess.get(allMess.size() - 1 - i);
            }
            allMess = Arrays.asList(sortMess);
        }

        return allMess;
    }

    /**
     * Method for finding duplicates in an array
     *
     * @param allMess  all transmitted messages
     * @param doubling duplication parameter value
     * @return depending on the parameter - messages with or without duplicates
     */
    private static List<Message> searchDuplicates(List<Message> allMess, Doubling doubling) {

        if (doubling == Doubling.DISTINCT) {
            Message[] doublingMess = new Message[allMess.size()];
            int k = 0;
            boolean isDoubling = false;
            for (Message mess : allMess) {
                for (Message message : doublingMess) {
                    if (message != null && mess != null && Objects.equals(message.getBody(), mess.getBody())) {
                        isDoubling = true;
                        break;
                    }
                }
                if (!isDoubling) {
                    doublingMess[k] = mess;
                    k++;
                }
                isDoubling = false;
            }
            allMess = Arrays.asList(doublingMess);
        }
        return allMess;
    }
}

