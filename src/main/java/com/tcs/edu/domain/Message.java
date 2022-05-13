package com.tcs.edu.domain;

import com.tcs.edu.enumeration.Severity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return severityLevel == message.severityLevel &&
                Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(severityLevel, body);
    }

    @Override
    public String toString() {
        return "Message{" +
                "severityLevel=" + severityLevel +
                ", body='" + body + '\'' +
                '}';
    }
}
