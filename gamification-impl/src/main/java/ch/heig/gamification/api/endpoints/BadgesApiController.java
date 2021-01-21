package ch.heig.gamification.api.endpoints;

import ch.heig.gamification.api.BadgesApi;
import ch.heig.gamification.api.model.Badge;
import ch.heig.gamification.entities.BadgeEntity;
import ch.heig.gamification.repositories.BadgeRepository;
import ch.heig.gamification.entities.ApplicationEntity;
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
public class BadgesApiController implements BadgesApi {

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    ServletRequest servletRequest;

    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createBadge(@ApiParam(name = "", required = true) @Valid @RequestBody Badge badge) {
        BadgeEntity newBadgeEntity = toBadgeEntity(badge);
        BadgeEntity alreadyThere = badgeRepository.findByApplicationEntityAndName(
                (ApplicationEntity)servletRequest.getAttribute("appEntity"), badge.getName());

        if((alreadyThere != null) && (alreadyThere.getName().equals(newBadgeEntity.getName()))) {
            return ResponseEntity.status(HttpStatus.IM_USED).build(); // bien choisir le status de retour
        } else {
            badgeRepository.save(newBadgeEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newBadgeEntity.getId()).toUri();


            return ResponseEntity.created(location).build();
        }
    }

    private BadgeEntity toBadgeEntity(Badge badge) {
        BadgeEntity entity = new BadgeEntity();
        entity.setName(badge.getName());
        entity.setApplicationEntity((ApplicationEntity)servletRequest.getAttribute("appEntity"));
        return entity;
    }
}
