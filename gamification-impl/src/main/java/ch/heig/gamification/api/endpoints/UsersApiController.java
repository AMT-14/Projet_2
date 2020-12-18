package ch.heig.gamification.api.endpoints;

import ch.heig.gamification.api.model.User;
import ch.heig.gamification.api.model.Badge;
import ch.heig.gamification.api.UsersApi;
import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.UserEntity;
import ch.heig.gamification.entities.BadgeEntity;
import ch.heig.gamification.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    ServletRequest request;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<List<User>> getUsers() {
        HttpServletRequest req = (HttpServletRequest) request;
        ApplicationEntity applicationEntity = (ApplicationEntity) req.getAttribute("appEntity");
        List<User> users = new ArrayList<>();
        for(UserEntity userEntity : userRepository.findAllByAppEntity(applicationEntity)){
            users.add(toUser(userEntity)); // transforme userEntity -> User
        }
        return ResponseEntity.ok(users);
    }


    public ResponseEntity<User> getUser(@ApiParam(value = "", required = true) @PathVariable("id") String id){
        HttpServletRequest req = (HttpServletRequest) request;
        ApplicationEntity applicationEntity = (ApplicationEntity) req.getAttribute("appEntity");
        long longId = Long.valueOf(id);
        UserEntity userEntity = userRepository.findById(longId);
       if(userEntity != null){
           return null;//ResponseEntity.ok(toUser(userEntity));
       } else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private Badge toBadge(BadgeEntity badgeEntity){
        Badge badge = new Badge();
        badge.setName(badgeEntity.getName());
        return badge;
    }

    private User toUser(UserEntity userEntity){
        User user = new User();
        return user;
    }



}
