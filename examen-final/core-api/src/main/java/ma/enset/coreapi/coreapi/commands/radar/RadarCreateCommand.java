package ma.enset.coreapi.coreapi.commands.radar;

import ma.enset.coreapi.coreapi.commands.BaseCommand;
import lombok.Getter;

public class RadarCreateCommand extends BaseCommand<String> {
    @Getter private double vitesseMax;
    @Getter private double longitude;
    @Getter private double latitude;
    public RadarCreateCommand(String id, double vitesseMax, double longitude, double latitude) {
        super(id);
        this.vitesseMax = vitesseMax;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
