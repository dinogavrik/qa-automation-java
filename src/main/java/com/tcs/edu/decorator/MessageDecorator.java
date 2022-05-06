package com.tcs.edu.decorator;

/**
 * Interface with message decoration methods
 */
public interface MessageDecorator {
    /**
     * Method for decorating a message
     *
     * @param decorateMessages decorated message
     * @return decorated message
     */
    String decorate(String decorateMessages);
}
