package org.example.service;

import org.example.model.Planet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PlanetCrudService {

    @PersistenceContext
    private EntityManager entityManager;

    public Planet createPlanet(String id, String name) {
        Planet planet = new Planet(id, name);
        entityManager.persist(planet);
        return planet;
    }

    public Planet getPlanet(String id) {
        return entityManager.find(Planet.class, id);
    }

    public Planet updatePlanet(String id, String name) {
        Planet planet = getPlanet(id);
        if (planet != null) {
            planet.setName(name);
            entityManager.merge(planet);
        }
        return planet;
    }

    public void deletePlanet(String id) {
        Planet planet = getPlanet(id);
        if (planet != null) {
            entityManager.remove(planet);
        }
    }

    public void setEntityManager(EntityManager entityManager) {
    }
}
