package org.example.service;

import org.example.dao.impl.PlanetDAO;
import org.example.entity.Planet;

import java.util.List;
import java.util.Objects;

import static org.example.util.ServiceUtil.isIdValid;
import static org.example.util.ServiceUtil.isPlanetValid;

public class PlanetService {

    private final PlanetDAO planetDAO = new PlanetDAO();

    public Planet getById(String id) {
        return planetDAO.findById(id);
    }

    public List<Planet> getAll() {
        return planetDAO.findAll();
    }

    public void add(Planet planet) {
        if (!isPlanetValid(planet)) {
            throw new IllegalArgumentException("Invalid planet id or name");
        }
        Planet planedById = planetDAO.findById(planet.getId());

        if (planedById != null) {
            throw new IllegalStateException("Planet with id '" + planet.getId() + "' already exists");
        }
        Planet planetByName = planetDAO.findByName(planet.getName());

        if (planetByName != null) {
            throw new IllegalStateException("Planet with name '" + planet.getName() + "' already exists");
        }
        planetDAO.save(planet);
    }

    public void update(Planet planet) {
        if (!isPlanetValid(planet)) {
            throw new IllegalArgumentException("Invalid planet id or name");
        }
        Planet planetById = planetDAO.findById(planet.getId());

        if (planetById == null) {
            throw new IllegalStateException("Planet with id '" + planet.getId() + "' does not exist");
        }

        if (!Objects.equals(planet.getName(), planetById.getName())) {
            Planet planetByName = planetDAO.findByName(planet.getName());

            if (planetByName != null) {
                throw new IllegalStateException("Planet with name '" + planet.getName() + "' already exists");
            }
            planetDAO.update(planet);
        }
    }

    public void delete(Planet planet) {
        planetDAO.delete(planet);
    }

    public Planet deleteById(String id) {
        if (!isIdValid(id)) {
            throw new IllegalArgumentException("Invalid planet id");
        }
        Planet planetById = planetDAO.findById(id);

        if (planetById == null) {
            throw new IllegalStateException("Planet with id '" + id + "' does not exist");
        }
        return planetDAO.deleteById(id);
    }
}
