package ch.heig.gamification.entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Data
public class RuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String event;
    private String badgeName;
    private Long scoreScaleId;
    private int scoreDelta;

    @ManyToOne
    private ApplicationEntity applicationEntity;

    @ManyToOne
    private ScoreScaleEntity scoreScaleEntity;
}
