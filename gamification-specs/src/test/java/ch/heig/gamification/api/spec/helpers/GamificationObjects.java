package ch.heig.gamification.api.spec.helpers;

import ch.heig.gamification.api.dto.*;
import lombok.Getter;
import lombok.Setter;

public class GamificationObjects {

    @Getter
    @Setter
    static Application application;

    @Getter
    @Setter
    User user;

    @Getter
    @Setter
    Event event;

    @Getter
    @Setter
    ScoreScale scoreScale;

    @Getter
    @Setter
    Badge badge;

    @Getter
    @Setter
    Rule rule;

    //TODO add last created Object for each ?


}
