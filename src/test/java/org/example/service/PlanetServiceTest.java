package org.example.service;

import org.example.entity.Planet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlanetServiceTest {

    static PlanetService planetService;

    @BeforeAll
    static void setup() {
        planetService = new PlanetService();
    }

    @AfterAll
    static void cleanup() {
        planetService = null;
    }

    @ParameterizedTest
    @CsvSource(value = {"K7R-Terra Nova", "P5M-Zephyr", "L3H-Helios"}, delimiter = '-')
    void test_add_planet(String id, String name) {
        Planet planet = buildPlanet(id, name);
        planetService.add(planet);

        Planet planetById = planetService.getById(id);

        assertEquals(planet.getName(), planetById.getName());

        planetService.deleteById(id);
    }

    @ParameterizedTest
    @CsvSource(value = {"NEP-Nexus", "SAT-Aquila", "JUP-Titan"}, delimiter = '-')
    void test_update_planet(String id, String name) {
        Planet planet = planetService.getById(id);
        planet.setName(name);
        planetService.update(planet);

        Planet planetById = planetService.getById(id);

        assertEquals(name, planetById.getName());
    }

    @ParameterizedTest
    @CsvSource(value = {"W4Z-Zenith", "X1D-Draco", "Y5C-Cyra"}, delimiter = '-')
    void test_delete_client(String id, String name) {
        Planet planet = buildPlanet(id, name);
        planetService.add(planet);

        planetService.deleteById(id);

        assertNull(planetService.getById(id));
    }

    private Planet buildPlanet(String id, String name) {
        Planet planet = new Planet();
        planet.setId(id);
        planet.setName(name);
        return planet;
    }
}