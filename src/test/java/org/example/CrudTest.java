package org.example;

import org.example.model.Client;
import org.example.model.Planet;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class CrudTests {

    private EntityManager entityManager;
    private ClientCrudService clientService;
    private PlanetCrudService planetService;

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-pu");
        entityManager = emf.createEntityManager();
        clientService = new ClientCrudService();
        clientService.setEntityManager(entityManager);
        planetService = new PlanetCrudService();
        planetService.setEntityManager(entityManager);
    }

    @Test
    void testClientCrudOperations() {
        // Create
        Client client = clientService.createClient("Test Client");
        assertNotNull(client.getId());

        // Read
        Client retrievedClient = clientService.getClient(client.getId());
        assertEquals("Test Client", retrievedClient.getName());

        // Update
        Client updatedClient = clientService.updateClient(client.getId(), "Updated Client");
        assertEquals("Updated Client", updatedClient.getName());

        // Delete
        clientService.deleteClient(client.getId());
        assertNull(clientService.getClient(client.getId()));
    }

    @Test
    void testPlanetCrudOperations() {
        // Create
        Planet planet = planetService.createPlanet("PLANET1", "Test Planet");
        assertNotNull(planet.getId());

        // Read
        Planet retrievedPlanet = planetService.getPlanet(planet.getId());
        assertEquals("Test Planet", retrievedPlanet.getName());

        // Update
        Planet updatedPlanet = planetService.updatePlanet(planet.getId(), "Updated Planet");
        assertEquals("Updated Planet", updatedPlanet.getName());

        // Delete
        planetService.deletePlanet(planet.getId());
        assertNull(planetService.getPlanet(planet.getId()));
    }
}
