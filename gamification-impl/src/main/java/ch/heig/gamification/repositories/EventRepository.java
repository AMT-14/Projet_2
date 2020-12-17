package ch.heig.gamification.repositories;

import ch.heig.gamification.entities.EventEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventEntity, Long> {

}
