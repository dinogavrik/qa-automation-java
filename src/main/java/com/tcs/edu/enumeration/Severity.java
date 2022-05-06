package com.tcs.edu.enumeration;

/**
 * Importance levels that can be used
 * {@link #MINOR}
 * {@link #REGULAR}
 * {@link #MAJOR}
 *
 * @author Gavrikova Irina
 */
public enum Severity {
    /**
     * Minor message
     */
    MINOR ("()"),

    /**
     * Regular message
     */
    REGULAR ("(!)"),

    /**
     * Important message
     */
    MAJOR ("(!!!)");

    /**
     * Message level
     */
    private final String level;

    /**
     * Constructor
     * @param level level value
     */
    Severity(String level) {
       this.level = level;
    }

    /**
     * Getting the message level value
     * @return level value
     */
    public String getLevel() {
        return level;
    }
}
