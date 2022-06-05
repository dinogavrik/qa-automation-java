package com.tcs.edu.service;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enumeration.Severity;

import java.util.Collection;
import java.util.UUID;

/**
 * Interface that defines the processing
 * logic for message assembly
 */
public interface MessageService {

    UUID create(Message message);

    Message getById(UUID key);

    Collection<Message> get();

    Collection<Message> get(Severity severity);

    Collection<Message> get(String body);

    Collection<Message> get(Severity severity, String body);

    Message put(Message message);

    Message delete(UUID id);

//    /**
//     * Method for assembling the output message
//     *
//     * @param message  transmitted message
//     * @param messages transmitted messages
//     * @see #process(MessageOrder, Message, Message...)
//     */
//    void process(Message message, Message... messages);
//
//    /**
//     * Method for assembling a message with sorting
//     *
//     * @param order    sort
//     * @param message  transmitted message
//     * @param messages transmitted messages
//     * @see #process(MessageOrder, Doubling, Message, Message...)
//     */
//    void process(MessageOrder order, Message message, Message... messages);
//
//    /**
//     * Method for assembling a sort message with doubling or not
//     *
//     * @param order    sort
//     * @param doubling duplication parameter value
//     * @param message  transmitted message
//     * @param messages transmitted messages
//     */
//    void process(MessageOrder order, Doubling doubling, Message message, Message... messages);
}
