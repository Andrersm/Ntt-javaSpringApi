package org.example.superapiv1.exception;

public class EntityDeletionException extends RuntimeException {
    public EntityDeletionException(String entityName, Long id) {
        super(String.format("%s com ID %d não encontrado e, portanto, não pode ser apagado.", entityName, id));
    }
}