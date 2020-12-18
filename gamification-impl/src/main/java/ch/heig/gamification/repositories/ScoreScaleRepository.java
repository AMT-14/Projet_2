package ch.heig.gamification.repositories;

import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.BadgeEntity;
import ch.heig.gamification.entities.ScoreScaleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ScoreScaleRepository extends CrudRepository<ScoreScaleEntity, Long> {
    ScoreScaleEntity findByApplicationEntityAndId(ApplicationEntity applicationEntity, long scoreScaleId);
    ScoreScaleEntity findByApplicationEntityAndName(ApplicationEntity applicationEntity, String name);

}
