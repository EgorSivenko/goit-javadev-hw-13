package org.example.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
