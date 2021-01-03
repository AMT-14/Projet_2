package ch.heig.gamification.api.services;


import ch.heig.gamification.entities.*;
import ch.heig.gamification.repositories.BadgeRewardRepository;
import ch.heig.gamification.repositories.ScoreRewardRepository;
import ch.heig.gamification.repositories.ScoreScaleRepository;
import ch.heig.gamification.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventProcessor {

    @Autowired
    ScoreScaleRepository scoreScaleRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private ScoreRewardRepository scoreRewardRepository;

    @Autowired
    private BadgeRewardRepository badgeRewardRepository;


    @Async
    @Transactional
    public void process(EventEntity event){
        List<RuleEntity> rules = ruleRepository.findAllByApplicationEntity(event.getApplicationEntity());

        for (RuleEntity rule : rules){
            if(rule.getEventName().equals(event.getName())){
                if(rule.getScoreScaleEntity() != null) {
                    ScoreRewardEntity rewardEntity = new ScoreRewardEntity();
                    rewardEntity.setApplicationEntity(event.getApplicationEntity());
                    rewardEntity.setUserEntity(event.getUserEntity());
                    rewardEntity.setTimeStamp(event.getCreationDateTime());
                    rewardEntity.setScoreScaleEntity(rule.getScoreScaleEntity());
                    rewardEntity.setDelta(rule.getScoreDelta());
                    scoreRewardRepository.save(rewardEntity);
                }
                if(badgeRewardRepository.findByUserEntityAndBadgeEntity(event.getUserEntity(), rule.getBadgeEntity()) == null) {
                    BadgeRewardEntity rewardEntity = new BadgeRewardEntity();
                    rewardEntity.setApplicationEntity(event.getApplicationEntity());
                    rewardEntity.setUserEntity(event.getUserEntity());
                    rewardEntity.setTimeStamp(event.getCreationDateTime());
                    rewardEntity.setBadgeEntity(rule.getBadgeEntity());
                    badgeRewardRepository.save(rewardEntity);
                }
            }
        }
    }
}
