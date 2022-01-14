package ma.enset.coreapi.coreapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CreateRadarRequestDto {
    private double vitesseMax;
    private double longitude;
    private double latitude;
}
