package ch.heig.gamification.api.endpoints;

import ch.heig.gamification.api.EventsApi;
import ch.heig.gamification.api.model.Event;
import ch.heig.gamification.api.services.EventProcessor;
import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.EventEntity;
import ch.heig.gamification.entities.UserEntity;
import ch.heig.gamification.repositories.EventRepository;
import ch.heig.gamification.repositories.ScoreScaleRepository;
import ch.heig.gamification.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Date;

@Controller
public class EventsApiController implements EventsApi {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServletRequest servletRequest;

    @Autowired
    EventProcessor eventProcessor;


    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> registerEvent(@ApiParam(name = "", required = true) @Valid @RequestBody Event event){
        EventEntity eventEntity = toEventEntity(event);

        eventRepository.save(eventEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(eventEntity.getId()).toUri();
        eventProcessor.process(eventEntity);
        return ResponseEntity.created(location).build();

    }

    EventEntity toEventEntity(Event event) {
        ApplicationEntity applicationEntity = (ApplicationEntity) servletRequest.getAttribute("appEntity");
        UserEntity userEntity = userRepository.findByInGamifiedAppUserIdAndAppEntity(event.getInGamifiedAppUserId(), applicationEntity);
        if (userEntity == null){
            userEntity = new UserEntity();
            userEntity.setAppEntity(applicationEntity);
            userEntity.setInGamifiedAppUserId(event.getInGamifiedAppUserId());
            userRepository.save(userEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{inGamifiedApplicationUser}")
                    .buildAndExpand(userEntity.getInGamifiedAppUserId()).toUri();
        }
        EventEntity eventEntity = new EventEntity();
        eventEntity.setApplicationEntity(applicationEntity);
        eventEntity.setUserEntity(userEntity);
        eventEntity.setName(event.getName());
        eventEntity.setCreationDateTime(new Date());
        eventEntity.setProperties(event.getProperties());

        return eventEntity;
    }
}
