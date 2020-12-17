package ch.heig.gamification.entities;

import ch.heig.gamification.api.model.ScoreScale;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ScoreRewardEntity extends RewardEntity{
    @ManyToOne
    private ScoreScaleEntity scoreScaleEntity;

    private int delta;
}
