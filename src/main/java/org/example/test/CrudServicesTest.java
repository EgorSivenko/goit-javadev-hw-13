package org.example.test;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.service.ClientService;
import org.example.service.PlanetService;
import org.example.util.HibernateUtil;
import org.hibernate.exception.ConstraintViolationException;

public class CrudServicesTest {
    public static void main(String[] args) {
        testClientService();
        testPlanetService();

        HibernateUtil.closeSessionFactory();
    }

    public static void testClientService() {
        ClientService clientService = new ClientService();

        System.out.println("All clients: " + clientService.getAll());

        Client clientById = clientService.getById(3L);
        System.out.println("Client name with id '3': " + clientById.getName());

        Client client = new Client();
        client.setName("John");

        clientService.add(client);
        Long id = client.getId();
        System.out.println("Client saved with id: " + id);

        client.setName("Luke");
        clientService.update(client);
        System.out.println("Updated the client: " + clientService.getById(id));

        clientService.delete(client);
        boolean wasRemoved = clientService.getById(id) == null;
        System.out.println("Client was removed: " + wasRemoved);

        // removing a client without tickets
        Client removedClient = clientService.deleteById(8L);
        System.out.println("Removed client: " + removedClient);
    }

    public static void testPlanetService() {
        PlanetService planetService = new PlanetService();

        System.out.println("All Planets: " + planetService.getAll());

        Planet planetById = planetService.getById("VEN");
        System.out.println("Planet name with id 'VEN': " + planetById.getName());

        Planet planet = new Planet();
        planet.setId("URN");
        planet.setName("Uranus");

        planetService.add(planet);
        System.out.println("Saved a new planet: " + planetService.getById("URN"));

        planet.setName("Uran");
        planetService.update(planet);
        System.out.println("Updated the planet: " + planetService.getById("URN"));

        planetService.delete(planet);
        boolean wasRemoved = planetService.getById("URN") == null;
        System.out.println("Planet was removed: " + wasRemoved);

        try {
            Planet removedPlanet = planetService.deleteById("SAT");
            System.out.println("Removed planet: " + removedPlanet);
        } catch (ConstraintViolationException e) {
            System.err.println(e.getConstraintName());
        }
    }
}
