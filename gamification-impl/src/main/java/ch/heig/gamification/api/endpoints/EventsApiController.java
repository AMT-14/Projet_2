package ch.heig.gamification.api.endpoints;

import ch.heig.gamification.api.EventsApi;
import ch.heig.gamification.api.model.Event;
import ch.heig.gamification.api.model.ScoreScale;
import ch.heig.gamification.entities.EventEntity;
import ch.heig.gamification.repositories.EventsRepository;
import ch.heig.gamification.repositories.ScoreScaleRepository;
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

@Controller
public class EventsApiController implements EventsApi {
    @Autowired
    EventsRepository eventsRepository;

    @Autowired
    ServletRequest servletRequest;

    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createEvent(@ApiParam(name = "", required = true) @Valid @RequestBody Event event){
        EventEntity eventEntity = toEventEntity(event);
        eventsRepository.save(eventEntity);

        servletRequest.getAttribute("eventEntity");

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(eventEntity.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    EventEntity toEventEntity(Event event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setUserId(Integer.parseInt(event.getUserID()));
        eventEntity.setName(event.getName());
        eventEntity.setTime(event.getTime());
        return eventEntity;
    }
}