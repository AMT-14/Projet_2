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
    private String event;
    private String badgeName; // voir plus bas
    private String scoreScaleId;
    private int scoreDelta;

    //many to one to badge entity

    @ManyToOne
    private ApplicationEntity applicationEntity;

    @ManyToOne
    private ScoreScaleEntity scoreScaleEntity;
}
