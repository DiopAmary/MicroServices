package ma.enset.coreapi.coreapi.events.radar;

import ma.enset.coreapi.coreapi.events.BaseEvent;
import lombok.Getter;

import java.util.Date;

public class RadarCreatedEvent extends BaseEvent<String> {
    @Getter private double vitesseMax;
    @Getter private double longitude;
    @Getter private double latitude;
    public RadarCreatedEvent(String id, Date dateEvent, double vitesseMax, double longitude, double latitude) {
        super(id, dateEvent);
        this.vitesseMax = vitesseMax;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
