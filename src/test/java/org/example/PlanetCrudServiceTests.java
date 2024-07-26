package org.example;

import org.example.model.Planet;
import org.example.service.PlanetCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class PlanetCrudServiceTests {

    private EntityManager entityManager;
    private PlanetCrudService planetService;

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-pu");
        entityManager = emf.createEntityManager();
        planetService = new PlanetCrudService();
        planetService.setEntityManager(entityManager);
    }

    @Test
    void testCreatePlanet() {
        Planet planet = planetService.createPlanet("PLANET1", "Test Planet");
        assertNotNull(planet.getId());
    }

    @Test
    void testGetPlanet() {
        Planet planet = planetService.createPlanet("PLANET1", "Test Planet");
        Planet retrievedPlanet = planetService.getPlanet(planet.getId());
        assertEquals("Test Planet", retrievedPlanet.getName());
    }

    @Test
    void testUpdatePlanet() {
        Planet planet = planetService.createPlanet("PLANET1", "Test Planet");
        Planet updatedPlanet = planetService.updatePlanet(planet.getId(), "Updated Planet");
        assertEquals("Updated Planet", updatedPlanet.getName());
    }

    @Test
    void testDeletePlanet() {
        Planet planet = planetService.createPlanet("PLANET1", "Test Planet");
        planetService.deletePlanet(planet.getId());
        assertNull(planetService.getPlanet(planet.getId()));
    }
}
