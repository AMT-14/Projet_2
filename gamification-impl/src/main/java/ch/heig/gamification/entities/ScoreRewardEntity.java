package ch.heig.gamification.entities;

import ch.heig.gamification.api.model.ScoreScale;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ScoreRewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    @ManyToOne
    private ApplicationEntity applicationEntity;

    @ManyToOne
    private UserEntity userEntity;


    @ManyToOne
    private ScoreScaleEntity scoreScaleEntity;

    private int delta;
}
