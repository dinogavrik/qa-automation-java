package com.tcs.edu.domain;

import com.tcs.edu.enumeration.Severity;
import com.tcs.edu.service.ProcessException;

import java.util.Objects;
import java.util.UUID;

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


    private UUID id;


    /**
     * Constructor
     *
     * @param severityLevel message level
     * @param body          message
     */


    public Message(Severity severityLevel, String body) {
        try {
            isArgsValid(severityLevel, body);
        } catch (IllegalArgumentException e) {
            throw new ProcessException("Переданы невалидные аргументы", e);
        }

        this.severityLevel = severityLevel;
        this.body = body;
    }

    public Message setId(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getId() {
        return id;
    }

    /**
     * @return Getting message level
     */
    public Severity getSeverityLevel() {
        return severityLevel;
    }

    /**
     * Getting message body
     *
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
        return
                "{id= " +
                        id +
                        ", severityLevel= " + severityLevel +
                        ", body='" + body + '\'' +
                        '}';
    }

    private boolean isArgsValid(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                throw new IllegalArgumentException("Оne or all parameters are null");
            }
        }
        return true;
    }


}
