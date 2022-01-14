package ma.enset.radarservice.commands.services;

import ma.enset.coreapi.coreapi.dtos.CreateRadarRequestDto;

import java.util.concurrent.CompletableFuture;

public interface RadarService {
    CompletableFuture<String> createRadar(CreateRadarRequestDto createRadarRequestDto);
}
