package ch.heig.gamification.repositories;

import ch.heig.gamification.entities.UserEntity;
import ch.heig.gamification.entities.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findById(long id);
    UserEntity findByInGamifiedAppUserIdAndAppEntity(String inGamifiedAppUserID, ApplicationEntity applicationEntity);
    List<UserEntity> findAllByAppEntity(ApplicationEntity applicationEntity);
}
