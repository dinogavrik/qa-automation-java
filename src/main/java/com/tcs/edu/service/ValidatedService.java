package com.tcs.edu.service;

/**
 * Contains a set of common
 * methods for validation
 */
public abstract class ValidatedService extends ProcessException {

    /**
     * Method for validating incoming arguments
     * @param objects vararg objects
     * @throws IllegalArgumentException for invalid arguments
     * @return successful validation
     */
    boolean isArgsValid(Object ... objects) {
        for (Object object: objects) {
            if (object == null) {
                throw new IllegalArgumentException("Оne or all parameters are null");
            }
        }
        return true;
    }
}
