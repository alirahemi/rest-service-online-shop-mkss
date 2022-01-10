package de.hsbremen.mkss.restservice.events;

public interface CrudEventProducer<T> {

    void emitCreateEvent(T payload);
    void emitUpdateEvent(T payload);
    void emitDeleteEvent(T payload);

}
