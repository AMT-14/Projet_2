package ch.heig.gamification.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class EventEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long userId;

    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;

    @ManyToOne
    private ApplicationEntity applicationEntity;

}