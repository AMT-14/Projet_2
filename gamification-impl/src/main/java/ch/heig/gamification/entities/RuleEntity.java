package ch.heig.gamification.entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class RuleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String eventName;
    private int scoreDelta;

    @OneToOne
    private BadgeEntity badgeEntity;

    @ManyToOne
    private ApplicationEntity applicationEntity;

    @OneToOne
    private ScoreScaleEntity scoreScaleEntity;
}
