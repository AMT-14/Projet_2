package ch.heig.gamification.repositories;


import ch.heig.gamification.entities.RewardEntity;
import org.springframework.data.repository.CrudRepository;

public interface RewardRepository extends CrudRepository<RewardEntity, Long> {
    //TODO implémenter getBadgesFromUser getScoresFromUser à partir des queries
}
