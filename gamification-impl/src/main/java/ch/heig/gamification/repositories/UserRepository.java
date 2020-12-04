package ch.heig.gamification.repositories;

import ch.heig.gamification.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByInAppIdAndApiKey(UUID inAppId, UUID apiKey);
    List<UserEntity> findAllByApiKey(UUID apiKey);
}