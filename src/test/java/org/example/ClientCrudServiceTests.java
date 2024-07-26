package org.example;

import org.example.model.Client;
import org.example.service.ClientCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ClientCrudServiceTests {

    private EntityManager entityManager;
    private ClientCrudService clientService;

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-pu");
        entityManager = emf.createEntityManager();
        clientService = new ClientCrudService();
        clientService.setEntityManager(entityManager);
    }

    @Test
    void testCreateClient() {
        Client client = clientService.createClient("Test Client");
        assertNotNull(client.getId());
    }

    @Test
    void testGetClient() {
        Client client = clientService.createClient("Test Client");
        Client retrievedClient = clientService.getClient(client.getId());
        assertEquals("Test Client", retrievedClient.getName());
    }

    @Test
    void testUpdateClient() {
        Client client = clientService.createClient("Test Client");
        Client updatedClient = clientService.updateClient(client.getId(), "Updated Client");
        assertEquals("Updated Client", updatedClient.getName());
    }

    @Test
    void testDeleteClient() {
        Client client = clientService.createClient("Test Client");
        clientService.deleteClient(client.getId());
        assertNull(clientService.getClient(client.getId()));
    }
}
