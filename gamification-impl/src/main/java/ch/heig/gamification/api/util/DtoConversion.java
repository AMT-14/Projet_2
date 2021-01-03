package ch.heig.gamification.api.util;

import ch.heig.gamification.api.model.Badge;
import ch.heig.gamification.entities.ApplicationEntity;
import ch.heig.gamification.entities.BadgeEntity;

public final class DtoConversion {

    private DtoConversion(){};

    public static Badge toBadge(BadgeEntity badgeEntity) {
        Badge badge = new Badge();
        badge.setName(badgeEntity.getName());
        //badge.setApplicationEntity((ApplicationEntity)servletRequest.getAttribute("appEntity"));
        return badge;
    }




}
