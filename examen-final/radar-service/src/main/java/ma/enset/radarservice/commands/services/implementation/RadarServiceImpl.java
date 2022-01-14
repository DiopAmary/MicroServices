package ma.enset.radarservice.commands.services.implementation;


import ma.enset.coreapi.coreapi.commands.radar.RadarCreateCommand;
import ma.enset.coreapi.coreapi.dtos.CreateRadarRequestDto;
import ma.enset.radarservice.commands.services.RadarService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class RadarServiceImpl implements RadarService {

    private CommandGateway commandGateway;

    public RadarServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createRadar(CreateRadarRequestDto createRadarRequestDto) {
        CompletableFuture<String> response = commandGateway.send(new RadarCreateCommand(
                UUID.randomUUID().toString(),
                createRadarRequestDto.getVitesseMax(),
                createRadarRequestDto.getLongitude(),
                createRadarRequestDto.getLatitude()
        ));
        return response;
    }
}
