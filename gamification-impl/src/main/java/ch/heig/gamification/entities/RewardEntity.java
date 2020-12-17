package ch.heig.gamification.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public abstract class RewardEntity implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    @ManyToOne
    private ApplicationEntity applicationEntity;

    @ManyToOne
    private UserEntity user;

    @Id
    private long id;
}
