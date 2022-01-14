package ma.enset.radarservice.commands.aggregates;

import ma.enset.coreapi.coreapi.commands.radar.RadarCreateCommand;
import ma.enset.coreapi.coreapi.events.radar.RadarCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
public class RadarAggregate {
@AggregateIdentifier
    private String radarId;

    private double vitesseMax;
    private double longitude;
    private double latitude;

    public RadarAggregate(){}

    @CommandHandler
    public RadarAggregate(RadarCreateCommand command) throws Exception {
        if(command.getVitesseMax() < 0) throw new Exception("vitesse negatif !!!!");
        AggregateLifecycle.apply(new RadarCreatedEvent(
                command.getId(),
                new Date(),
                command.getVitesseMax(),
                command.getLongitude(),
                command.getLatitude()
        ));
    }

    @EventSourcingHandler
    public void on(RadarCreatedEvent event){
        this.radarId = event.getId();
        this.vitesseMax = event.getVitesseMax();
        this.longitude = event.getLongitude();
        this.latitude = event.getLatitude();
    }
}
