package ch.heig.gamification.api.endpoints;

import ch.heig.gamification.api.model.ScoreScale;
import ch.heig.gamification.api.model.Rule;
import ch.heig.gamification.api.RulesApi;
import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.BadgeEntity;
import ch.heig.gamification.entities.RuleEntity;
import ch.heig.gamification.entities.ScoreScaleEntity;
import ch.heig.gamification.repositories.ApplicationRepository;
import ch.heig.gamification.repositories.BadgeRepository;
import ch.heig.gamification.repositories.RuleRepository;
import ch.heig.gamification.repositories.ScoreScaleRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Optional;

public class RulesApiController implements RulesApi{

    @Autowired
    ServletRequest req;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    ScoreScaleRepository scoreScaleRepository;
    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    RuleRepository ruleRepository;
    @Autowired
    ServletRequest servletRequest;

    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Rule> createRule(@ApiParam(value = "", required = true) @Valid @RequestBody Rule rule) {

        ApplicationEntity applicationEntity = (ApplicationEntity) req.getAttribute("appEntity");

        // TODO: check here if rule is wrong (check its attributes)
        // in this case return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // or NOT_found

        RuleEntity newRuleEntity = toRuleEntity(rule);
        RuleEntity alreadyThere = ruleRepository.findByApplicationEntityAndName(
                (ApplicationEntity)servletRequest.getAttribute("appEntity"), rule.getName());

        if((alreadyThere != null && alreadyThere.getName().equals(newRuleEntity.getName()))
                || newRuleEntity.getScoreScaleEntity() == null){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build(); // bien choisir le status de retour
        } else {


        /*
        if (newRuleEntity.getScoreScaleEntity() == null && newRuleEntity.getBadgeEntity() == null){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        */

            ruleRepository.save(newRuleEntity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newRuleEntity.getId()).toUri();

            return ResponseEntity.created(location).build();
        }
    }



    private RuleEntity toRuleEntity(Rule rule){
        ApplicationEntity applicationEntity = (ApplicationEntity) req.getAttribute("appEntity");
        long scoreScaleId = Long.valueOf(rule.getScoreScaleId());
        long badgeId = Long.valueOf(rule.getBadgeId());
        ScoreScaleEntity scoreScaleEntity = scoreScaleRepository.findByApplicationEntityAndId(applicationEntity, scoreScaleId);
        BadgeEntity badgeEntity = badgeRepository.findByApplicationEntityAndId(applicationEntity, badgeId);


        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setName(rule.getName());
        ruleEntity.setDescription(rule.getDescription());
        ruleEntity.setEventName(rule.getEventName());

        ruleEntity.setApplicationEntity(applicationEntity);
        ruleEntity.setScoreScaleEntity(scoreScaleEntity);
        ruleEntity.setBadgeEntity(badgeEntity);
        if(scoreScaleEntity == null){
            ruleEntity.setScoreDelta(0);
        }else {
            ruleEntity.setScoreDelta(rule.getScoreDelta());
        }
        return ruleEntity;
    }
}
