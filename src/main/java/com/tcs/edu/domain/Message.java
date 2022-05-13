package com.tcs.edu.domain;

import com.tcs.edu.enumeration.Severity;

/**
 * DTO for entity message
 */
public class Message {
    /**
     * Severity level of the message
     */
    private final Severity severityLevel;
    /**
     * Message body
     */
    private final String body;

    /**
     * Constructor
     * @param severityLevel message level
     * @param body message
     */
    public Message(Severity severityLevel, String body) {
        this.severityLevel = severityLevel;
        this.body = body;
    }

    /**
     *
     * @return Getting message level
     */
    public Severity getSeverityLevel() {
        return severityLevel;
    }

    /**
     * Getting message body
     * @return message body
     */
    public String getBody() {
        return body;
    }

}
