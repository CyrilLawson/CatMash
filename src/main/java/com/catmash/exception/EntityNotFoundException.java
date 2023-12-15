package com.catmash.exception;

import org.springframework.dao.DataAccessException;

import java.io.Serializable;

public class EntityNotFoundException extends DataAccessException {

    public EntityNotFoundException(String entityName, Serializable entityId) {
        super(String.format("No %s with id %s exists!", entityName, entityId));
    }
}
