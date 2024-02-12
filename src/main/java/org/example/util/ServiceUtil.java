package org.example.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;
import org.example.entity.Planet;
import org.example.entity.Ticket;

@UtilityClass
public class ServiceUtil {

    private static final String ID_REGEXP = "^[A-Z 0-9]+$";

    public static boolean isIdValid(String id) {
        return id.matches(ID_REGEXP);
    }

    public static boolean isNameValid(String name) {
        return name != null && !name.isBlank();
    }

    public static boolean isPlanetValid(Planet planet) {
        return isIdValid(planet.getId()) && isNameValid(planet.getName());
    }

    public static boolean isTicketValid(Ticket ticket) {
        return ObjectUtils.allNotNull(
                ticket.getClient(),
                ticket.getFromPlanet(),
                ticket.getToPlanet()
        ) && ticket.getFromPlanet() != ticket.getToPlanet();
    }
}
