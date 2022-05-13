package com.tcs.edu.service;

/**
 * Contains a set of common
 * methods for validation
 */
public abstract class ValidatedService {

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

    /**
     * Method for validating incoming arguments
     * @param object object
     * @param name name argument
     * @throws IllegalArgumentException for invalid arguments
     * @return exception or successful validation
     */
    boolean isArgsValid(Object object, String name) {
        if (object == null) {
            throw new IllegalArgumentException("Parameter '" + name + "' is null");
        }
        return true;
    }
}
