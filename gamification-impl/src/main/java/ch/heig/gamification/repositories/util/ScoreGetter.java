package ch.heig.gamification.repositories.util;

import ch.heig.gamification.api.model.ScoreScale;
import ch.heig.gamification.entities.ScoreScaleEntity;

public interface ScoreGetter {
    ScoreScaleEntity getScoreScaleEntity();
    int getPoint();
}
