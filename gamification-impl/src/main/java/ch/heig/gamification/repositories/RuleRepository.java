package ch.heig.gamification.repositories;

import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RuleRepository extends CrudRepository<RuleEntity, Long> {
    List<RuleEntity> findAllByApplicationEntity(ApplicationEntity applicationEntity);
}
