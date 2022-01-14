package ma.enset.radarservice.queries.services;

import lombok.AllArgsConstructor;
import ma.enset.coreapi.coreapi.events.radar.RadarCreatedEvent;
import ma.enset.coreapi.coreapi.queries.GetAllRadarsQuery;
import ma.enset.radarservice.commands.services.RadarService;
import ma.enset.radarservice.queries.entities.RadarEntity;
import ma.enset.radarservice.queries.repositories.RadarRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RadarServiceHandler {
    private RadarRepository radarRepository;

    @EventHandler
    public void on(RadarCreatedEvent event){
        // Create Account
        RadarEntity radarEntity = new RadarEntity();
        radarEntity.setId(event.getId());
        radarEntity.setVitesseMax(event.getVitesseMax());
        radarEntity.setLatitude(event.getLatitude());
        radarEntity.setLongitude(event.getLongitude());

        radarRepository.save(radarEntity);
    }

    @QueryHandler
    public List<RadarEntity> on(GetAllRadarsQuery query){
        return radarRepository.findAll();
    }
}
