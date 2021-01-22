package ch.heig.gamification.api.util;

import ch.heig.gamification.api.model.Badge;
import ch.heig.gamification.api.model.ScoreScale;

import ch.heig.gamification.entities.BadgeEntity;
import ch.heig.gamification.entities.ScoreScaleEntity;

public final class DtoConversion {

    private DtoConversion(){};

    public static Badge toBadge(BadgeEntity badgeEntity) {
        Badge badge = new Badge();
        badge.setName(badgeEntity.getName());
        return badge;
    }
    public static ScoreScale toScoreScale(ScoreScaleEntity entity) {
        ScoreScale scoreScale = new ScoreScale();
        scoreScale.setName(entity.getName());
        return scoreScale;
    }



}
