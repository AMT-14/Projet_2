package ch.heig.gamification.api.services;


import ch.heig.gamification.entities.*;
import ch.heig.gamification.repositories.RewardRepository;
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
    private RewardRepository rewardRepository;

    @Async
    @Transactional
    public void process(EventEntity event){
        List<RuleEntity> rules = ruleRepository.findAllByApplicationEntity(event.getApplicationEntity());

        for (RuleEntity rule : rules){
            if(rule.getEventName() == event.getName()){
                if(rule.getScoreScaleEntity() != null) {
                    ScoreRewardEntity rewardEntity = new ScoreRewardEntity();
                    rewardEntity.setApplicationEntity(event.getApplicationEntity());
                    rewardEntity.setUser(event.getUser());
                    rewardEntity.setTimeStamp(event.getCreationDateTime());
                    rewardEntity.setScoreScaleEntity(rule.getScoreScaleEntity());
                    rewardEntity.setDelta(rule.getScoreDelta());
                    rewardRepository.save(rewardEntity);
                }
                if(rule.getBadgeEntity() != null) { // TODO Verifier que le joueur ne possede pas déjà le Badge.
                    BadgeRewardEntity rewardEntity = new BadgeRewardEntity();
                    rewardEntity.setApplicationEntity(event.getApplicationEntity());
                    rewardEntity.setUser(event.getUser());
                    rewardEntity.setTimeStamp(event.getCreationDateTime());
                    rewardEntity.setBadgeEntity(rule.getBadgeEntity());
                    rewardRepository.save(rewardEntity);
                }
            }
        }




/*

        ScoreScaleEntity scoreScaleEntity = new ScoreScaleEntity();
        scoreScaleEntity.setApplicationEntity(event.getApplicationEntity());
        scoreScaleEntity.setName("eeeeee");
        scoreScaleRepository.save(scoreScaleEntity);


        ScoreRewardEntity rewardEntity = new ScoreRewardEntity();
        rewardEntity.setApplicationEntity(event.getApplicationEntity());
        rewardEntity.setUser(event.getUser());
        rewardEntity.setTimeStamp(event.getCreationDateTime());
        rewardEntity.setDelta(5);
        rewardEntity.setScoreScaleEntity(scoreScaleEntity);


        rewardRepository.save(rewardEntity);
*/

    }
}
