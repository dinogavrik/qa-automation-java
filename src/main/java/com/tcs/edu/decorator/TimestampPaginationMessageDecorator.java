package com.tcs.edu.decorator;

/**
 * Class containing methods for
 * completely decorating the message
 */
public class TimestampPaginationMessageDecorator implements MessageDecorator {

    /**
     * Methods for completely decorating
     * the message
     * @param decorateMessages decorated message
     * @return decorated message
     */
    public String decorate(String decorateMessages) {

        MessageDecorator timestampDecorator = new TimestampMessageDecorator();
        MessageDecorator paginationDecorator = new PaginationMessageDecorator();

        return timestampDecorator.decorate(paginationDecorator.decorate(decorateMessages));
    }
}
