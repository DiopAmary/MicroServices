package ma.enset.radarservice.queries.controllers;

import lombok.AllArgsConstructor;
import ma.enset.coreapi.coreapi.queries.GetAllRadarsQuery;
import ma.enset.radarservice.queries.entities.RadarEntity;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/queries/radar")
@AllArgsConstructor
public class RadarController {
    QueryGateway queryGateway;

    @GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE } )
    public List<RadarEntity> getAllAccount(){
        return queryGateway.query(new GetAllRadarsQuery(), ResponseTypes.multipleInstancesOf(RadarEntity.class)).join();
    }
}
