package org.example.dao.impl;

import org.example.dao.DAO;
import org.example.entity.Planet;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlanetDAO implements DAO<Planet, String> {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Planet findById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planet.class, id);
        }
    }

    public Planet findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Planet> query = session.createQuery("FROM Planet p WHERE p.name = :name");
            query.setParameter("name", name);
            return query.getResultStream()
                    .findFirst()
                    .orElse(null);
        }
    }

    @Override
    public List<Planet> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Planet", Planet.class).getResultList();
        }
    }

    @Override
    public void save(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        }
    }

    @Override
    public void update(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(planet);
            transaction.commit();
        }
    }

    @Override
    public void delete(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        }
    }

    @Override
    public Planet deleteById(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Planet planet = session.get(Planet.class, id);
            session.remove(planet);

            transaction.commit();
            return planet;
        }
    }
}
