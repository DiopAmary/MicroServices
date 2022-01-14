package ma.enset.radarservice.queries.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RadarEntity {
    @Id
    private String id;
    private double vitesseMax;
    private double longitude;
    private double latitude;
}
