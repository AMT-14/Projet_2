package ch.heig.gamification.repositories;

import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.ScoreRewardEntity;
import ch.heig.gamification.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ch.heig.gamification.repositories.util.ScoreGetter;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreRewardRepository extends CrudRepository<ScoreRewardEntity, Long> {
    @Query("SELECT s.scoreScaleEntity , SUM(s.delta) AS point FROM ScoreRewardEntity AS s WHERE s.userEntity = :u AND s.applicationEntity = :app GROUP BY s.scoreScaleEntity") // WHERE s.UserEntity = :u")
    List<ScoreGetter> countScorePoints (@Param("u") UserEntity userEntity, @Param("app")ApplicationEntity applicationEntity);
}
