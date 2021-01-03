package ch.heig.gamification.repositories;


import ch.heig.gamification.entities.BadgeEntity;
import ch.heig.gamification.entities.BadgeRewardEntity;
import ch.heig.gamification.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface BadgeRewardRepository extends CrudRepository<BadgeRewardEntity, Long> {
    //TODO implémenter getBadgesFromUser getScoresFromUser à partir des queries
    //@Query("SELECT DISTINCT badge_entity FROM reward_entity WHERE user=j1")
    List<BadgeGetter> findByUserEntity(UserEntity userEntity);
           // @Param("usr") UserEntity userEntity
    BadgeRewardEntity findByUserEntityAndBadgeEntity(UserEntity userEntity, BadgeEntity badgeEntity);
/*
    @Query("SELECT DISTINCT badge_entity FROM reward_entity")
    List<BadgeEntity> getScoresFromUser(UserEntity userEntity);
*/
}

