package ch.heig.gamification.api.endpoints;

import ch.heig.gamification.api.model.Badge;
import ch.heig.gamification.api.model.User;
import ch.heig.gamification.api.model.UserStat;
import ch.heig.gamification.api.UsersApi;
import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.UserEntity;
import ch.heig.gamification.entities.BadgeEntity;
import ch.heig.gamification.repositories.BadgeGetter;
import ch.heig.gamification.repositories.BadgeRewardRepository;
import ch.heig.gamification.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import ch.heig.gamification.api.util.DtoConversion;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.*;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    ServletRequest request;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BadgeRewardRepository badgeRewardRepository;

    public ResponseEntity<List<User>> getUsers() {
       //HttpServletRequest req = (HttpServletRequest) request;
        ApplicationEntity applicationEntity = (ApplicationEntity) request.getAttribute("appEntity");
        List<User> users = new ArrayList<>();
        for(UserEntity userEntity : userRepository.findAllByAppEntity(applicationEntity)){
            users.add(toUser(userEntity)); // transforme userEntity -> User
        }
        return ResponseEntity.ok(users);
    }


    public ResponseEntity<UserStat> getUser(@ApiParam(value = "", required = true) @PathVariable("inGamifiedApplicationUser") String id){
        HttpServletRequest req = (HttpServletRequest) request;
        ApplicationEntity applicationEntity = (ApplicationEntity) req.getAttribute("appEntity");
        UserEntity userEntity = userRepository.findByInGamifiedAppUserIdAndAppEntity(id, applicationEntity);


        if(userEntity != null){
           return ResponseEntity.ok(getStat(userEntity)); //ResponseEntity.ok(toUser(userEntity));
       } else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private User toUser(UserEntity userEntity){
        User user = new User();
        user.setInGamifiedAppUserId(userEntity.getInGamifiedAppUserId());
        return user;
    }

    private UserStat getStat(UserEntity userEntity){
        UserStat userStat = new UserStat();
        //TODO faire les requêtes
        userStat.setUser(toUser(userEntity));
        List<Badge> badges = Collections.emptyList();
        List<BadgeGetter> badgeEntities = badgeRewardRepository.findByUserEntity(userEntity);
        for (BadgeGetter badgeGetter : badgeEntities){
            badges.add(DtoConversion.toBadge(badgeGetter.getBadgeEntityId()));
        }
        userStat.setBadges(badges);

        //userStat.setBadges(rewardRepository.getBadgesFromUser(userEntity));
        // rewardRepository.getScoresFromUser(userEntity);

        return userStat;
    }

}
