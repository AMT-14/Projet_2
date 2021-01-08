package ch.heig.gamification.api.endpoints;

import ch.heig.gamification.api.model.*;
import ch.heig.gamification.api.UsersApi;
import ch.heig.gamification.entities.*;
import ch.heig.gamification.repositories.ScoreRewardRepository;
import ch.heig.gamification.repositories.util.BadgeGetterInterface;
import ch.heig.gamification.repositories.BadgeRewardRepository;
import ch.heig.gamification.repositories.UserRepository;
import ch.heig.gamification.repositories.util.ScoreGetter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import ch.heig.gamification.api.util.DtoConversion;

import javax.servlet.ServletRequest;
import java.util.*;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    ServletRequest request;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BadgeRewardRepository badgeRewardRepository;

    @Autowired
    ScoreRewardRepository scoreRewardRepository;

    public ResponseEntity<List<User>> getUsers() {
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("appEntity");
        List<User> users = new ArrayList<>();
        for(UserEntity userEntity : userRepository.findAllByAppEntity(applicationEntity)){
            users.add(toUser(userEntity));
        }
        return ResponseEntity.ok(users);
    }


    public ResponseEntity<UserStat> getUser(@ApiParam(value = "", required = true) @PathVariable("inGamifiedApplicationUser") String id){
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("appEntity");
        UserEntity userEntity = userRepository.findByInGamifiedAppUserIdAndAppEntity(id, applicationEntity);


        if(userEntity != null){
           return ResponseEntity.ok(getStat(userEntity, applicationEntity)); //ResponseEntity.ok(toUser(userEntity));
       } else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private User toUser(UserEntity userEntity){
        User user = new User();
        user.setInGamifiedAppUserId(userEntity.getInGamifiedAppUserId());
        return user;
    }

    private UserStat getStat(UserEntity userEntity, ApplicationEntity applicationEntity){
        UserStat userStat = new UserStat();

        userStat.setUser(toUser(userEntity));

        List<Badge> badges = new ArrayList();
        List<BadgeGetterInterface> badgeEntities = badgeRewardRepository.findByUserEntityAndApplicationEntity(userEntity, applicationEntity);
        for (BadgeGetterInterface badgeGetter : badgeEntities){
            badges.add(DtoConversion.toBadge(badgeGetter.getBadgeEntity()));
        }
        userStat.setBadges(badges);

        List<UserScore> userScores= new ArrayList();
        List<ScoreGetter> scoreEntities = scoreRewardRepository.countScorePoints(userEntity, applicationEntity);
        for (ScoreGetter scoreEntity : scoreEntities){
            UserScore userScore = new UserScore();
            userScore.setScoreName(scoreEntity.getScoreScaleEntity().getName());
            userScore.setScoreValue(scoreEntity.getPoint());
            userScores.add(userScore);
        }
        userStat.setScores(userScores);

        return userStat;
    }

}
