package ch.heig.gamification.repositories;

import ch.heig.gamification.entities.ScoreRewardEntity;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRewardRepository extends CrudRepository<ScoreRewardEntity, Long> {
}
