package org.example.service;

import org.example.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ClientCrudService {

    @PersistenceContext
    private EntityManager entityManager;

    public Client createClient(String name) {
        Client client = new Client(name);
        entityManager.persist(client);
        return client;
    }

    public Client getClient(Long id) {
        return entityManager.find(Client.class, id);
    }

    public Client updateClient(Long id, String name) {
        Client client = getClient(id);
        if (client != null) {
            client.setName(name);
            entityManager.merge(client);
        }
        return client;
    }

    public void deleteClient(Long id) {
        Client client = getClient(id);
        if (client != null) {
            entityManager.remove(client);
        }
    }

    public void setEntityManager(EntityManager entityManager) {
    }
}
