package ch.heig.gamification.repositories;

import ch.heig.gamification.entities.EventEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventsRepository extends CrudRepository<EventEntity, Long> {
}
