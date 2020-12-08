package ch.heig.gamification.api.endpoints;

import ch.heig.gamification.api.model.ScoreScale;
import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.RuleEntity;
import ch.heig.gamification.entities.ScoreScaleEntity;
import ch.heig.gamification.repositories.ApplicationRepository;
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

public class RulesApiController implements RulesApi{

    @Autowired
    ServletRequest req;
    HttpRequest request = (HttpRequest) req;

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    ScoreScaleRepository scoreScaleRepository;
    //@Autowired
   // BadgeRepository badgeRepository;
    @Autowired
    RuleRepository ruleRepository;

    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Rule> createRule(@ApiParam(value = "", required = true) @Valid @RequestBody Rule rule) {

        ApplicationEntity applicationEntity = (ApplicationEntity) req.getAttribute("appEntity");

        // TODO: check here if rule is wrong (check its attributes)
        // in this case return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // or NOT_found

        ScoreScaleEntity scoreScaleEntity = scoreScaleRepository.findById(rule.getScoreScaleId());
        BadgeEntity badgeEntity = badgeRepository.findByName(rule.getBadgeName());
        if (scoreScaleEntity == null || badgeEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        RuleEntity ruleEntity = toRuleEntity(rule);
        ruleEntity.setApplicationEntity(applicationEntity);
        ruleEntity.setScoreScaleEntity(scoreScaleEntity);
        ruleRepository.save(ruleEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(ruleEntity.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
    private RuleEntity toRuleEntity(Rule rule){
        RuleEntity ruleEntity = new RuleEntity();
        ruleEntity.setName(rule.getName());
        ruleEntity.setDescription(rule.getDescription());
        ruleEntity.setEvent(rule.getEvent());
        (rule.getBadgeName() != null) ? ruleEntity.setBadgeName(rule.getBadgeName) : ruleEntity.setBadgeName("");
        ruleEntity.setscoreDelta(rule.getScoreDelta());
        return ruleEntity;
    }
}
