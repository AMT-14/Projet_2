package ch.heig.gamification.repositories;

import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.BadgeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BadgeRepository extends CrudRepository<BadgeEntity, Long> {
    BadgeEntity findByApplicationEntityAndId(ApplicationEntity applicationEntity, long badgeId);
    BadgeEntity findByApplicationEntityAndName(ApplicationEntity applicationEntity, String name);
}
