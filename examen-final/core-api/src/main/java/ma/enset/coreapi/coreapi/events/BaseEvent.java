package ma.enset.coreapi.coreapi.events;

import lombok.Getter;

import java.util.Date;

public abstract class BaseEvent<T> {
    @Getter private T id;
    @Getter private Date dateEvent;

    public BaseEvent(T id, Date dateEvent) {
        this.id = id;
        this.dateEvent = dateEvent;
    }
}
