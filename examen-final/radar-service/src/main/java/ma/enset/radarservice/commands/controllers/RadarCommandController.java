package ma.enset.radarservice.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.coreapi.coreapi.dtos.CreateRadarRequestDto;
import ma.enset.radarservice.commands.services.RadarService;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/commands/radar")
@AllArgsConstructor
public class RadarCommandController {
    private RadarService radarService;
    private EventStore eventStore;

    @PostMapping(
            path = "/create",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public CompletableFuture<String> createRadar(
            @ModelAttribute CreateRadarRequestDto request
    ){
        CompletableFuture<String> response = radarService.createRadar(request);
        return response;
    }

    @GetMapping(path = "/event-store/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }
}
