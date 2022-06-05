package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enumeration.Severity;

import java.util.Collection;
import java.util.UUID;

public interface MessageRepository {
    UUID create(Message message);

    Message findByPrimaryKey(UUID key);

    Collection<Message> findAll();

    Collection<Message> findBySeverity(Severity by);

    Collection<Message> findByBody(String body);

    Collection<Message> findBySeverityAndBody(Severity severity,String body);

    Message update(Message message);

    Message delete(UUID id);


}
