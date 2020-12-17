package ch.heig.gamification.api.services;


import ch.heig.gamification.api.model.ScoreScale;
import ch.heig.gamification.entities.EventEntity;
import ch.heig.gamification.entities.RewardEntity;
import ch.heig.gamification.entities.ScoreRewardEntity;
import ch.heig.gamification.entities.ScoreScaleEntity;
import ch.heig.gamification.repositories.RewardRepository;
import ch.heig.gamification.repositories.ScoreScaleRepository;
import ch.heig.gamification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventProcessor {

    @Autowired
    ScoreScaleRepository scoreScaleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Async
    @Transactional
    public void process(EventEntity event){
        ScoreScaleEntity scoreScaleEntity = new ScoreScaleEntity();
        scoreScaleEntity.setApplicationEntity(event.getApplicationEntity());
        scoreScaleEntity.setName("test_ter");
        scoreScaleRepository.save(scoreScaleEntity);


        ScoreRewardEntity rewardEntity = new ScoreRewardEntity();
        rewardEntity.setApplicationEntity(event.getApplicationEntity());
        rewardEntity.setUser(event.getUser());
        rewardEntity.setTimeStamp(event.getCreationDateTime());
        rewardEntity.setDelta(5);
        rewardEntity.setScoreScaleEntity(scoreScaleEntity);


        rewardRepository.save(rewardEntity);


    }
}
