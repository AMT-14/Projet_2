package ch.heig.gamification.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class BadgeRewardEntity extends RewardEntity {
    @ManyToOne
    private BadgeEntity badgeEntity;
}
