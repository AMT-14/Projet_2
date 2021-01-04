package ch.heig.gamification.repositories.util;

import ch.heig.gamification.api.model.ScoreScale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score {
    private ScoreScale scoreScale;
    private long points;

    public Score(ScoreScale s, long p){
        this.scoreScale = s;
        this.points = p;
    }

}
