package com.tcs.edu.service;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enumeration.Doubling;
import com.tcs.edu.enumeration.MessageOrder;

/**
 * Interface that defines the processing
 * logic for message assembly
 */
public interface MessageService {
    /**
     * Method for assembling the output message
     *
     * @param message  transmitted message
     * @param messages transmitted messages
     * @see #process(MessageOrder, Message, Message...)
     */
    void process(Message message, Message... messages);

    /**
     * Method for assembling a message with sorting
     *
     * @param order    sort
     * @param message  transmitted message
     * @param messages transmitted messages
     * @see #process(MessageOrder, Doubling, Message, Message...)
     */
    void process(MessageOrder order, Message message, Message... messages);

    /**
     * Method for assembling a sort message with doubling or not
     *
     * @param order    sort
     * @param doubling duplication parameter value
     * @param message  transmitted message
     * @param messages transmitted messages
     */
    void process(MessageOrder order, Doubling doubling, Message message, Message... messages);
}
