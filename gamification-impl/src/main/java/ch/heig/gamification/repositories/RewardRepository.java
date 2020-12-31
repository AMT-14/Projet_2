package ch.heig.gamification.repositories;


import ch.heig.gamification.entities.RewardEntity;
import ch.heig.gamification.entities.BadgeEntity;
import ch.heig.gamification.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RewardRepository extends CrudRepository<RewardEntity, Long> {
    //TODO implémenter getBadgesFromUser getScoresFromUser à partir des queries
  /*  @Query("SELECT DISTINCT badge_entity FROM reward_entity WHERE user=:usr")
    List<BadgeEntity> getBadgesFromUser(                                    
            @Param("usr") UserEntity userEntity);

    @Query("SELECT DISTINCT badge_entity FROM reward_entity")
    List<BadgeEntity> getScoresFromUser(UserEntity userEntity);
*/
}
