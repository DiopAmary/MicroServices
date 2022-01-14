package ma.enset.radarservice.queries.repositories;

import ma.enset.radarservice.queries.entities.RadarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadarRepository extends JpaRepository<RadarEntity, String> {
}
