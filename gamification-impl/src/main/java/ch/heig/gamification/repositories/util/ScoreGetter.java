package ch.heig.gamification.repositories.util;

import ch.heig.gamification.api.model.ScoreScale;

public interface ScoreGetter {
    ScoreScale getScoreScaleEntity();
    int getPoints();
}
