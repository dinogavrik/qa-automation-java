package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enumeration.Severity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class HashMapMessageRepository implements MessageRepository {
    private final Map<UUID, Message> messages = new HashMap<>();

    @Override
    public UUID create(Message message) {
        UUID key = UUID.randomUUID();
        message.setId(key);
        messages.put(message.getId(), message);
        return message.getId();
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messages.get(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }

    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return messages.values()
                .stream()
                .filter(message -> message.getSeverityLevel() == by)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Message> findByBody(String body) {
        return messages.values()
                .stream()
                .filter(message -> message.getBody().equals(body))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Message> findBySeverityAndBody(Severity severity, String body) {
        return messages.values()
                .stream()
                .filter(message -> message.getSeverityLevel() == severity)
                .filter(message -> message.getBody().equals(body))
                .collect(Collectors.toList());
    }

    public Message update(Message updateMessage) {
        return messages.replace(updateMessage.getId(), updateMessage);
    }

    @Override
    public Message delete(UUID id) {
        return messages.remove(id);
    }

    @Override
    public String toString() {
        return "HashMapMessageRepository{" +
                "messages=" + messages +
                '}';
    }
}
