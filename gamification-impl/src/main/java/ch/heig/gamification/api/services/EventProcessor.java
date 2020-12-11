package ch.heig.gamification.api.services;


import ch.heig.gamification.entities.EventEntity;
import ch.heig.gamification.repositories.UserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventProcessor {

    private UserRepository userRepository;

    public EventProcessor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Async
    @Transactional
    public void process(EventEntity event){

    }
}
