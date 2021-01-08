package ch.heig.gamification.repositories;


import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.BadgeEntity;
import ch.heig.gamification.entities.BadgeRewardEntity;
import ch.heig.gamification.entities.UserEntity;
import ch.heig.gamification.repositories.util.BadgeGetterInterface;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface BadgeRewardRepository extends CrudRepository<BadgeRewardEntity, Long> {
    List<BadgeGetterInterface> findByUserEntityAndApplicationEntity(UserEntity userEntity, ApplicationEntity applicationEntity);
    BadgeRewardEntity findByUserEntityAndBadgeEntity(UserEntity userEntity, BadgeEntity badgeEntity);
}

