package org.example;

import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.service.TicketCrudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TicketCrudServiceTests {

    private EntityManager entityManager;
    private ClientCrudService clientService;
    private PlanetCrudService planetService;
    private TicketCrudService ticketService;

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-pu");
        entityManager = emf.createEntityManager();
        clientService = new ClientCrudService();
        clientService.setEntityManager(entityManager);
        planetService = new PlanetCrudService();
        planetService.setEntityManager(entityManager);
        ticketService = new TicketCrudService();
        ticketService.setEntityManager(entityManager);
    }

    @Test
    void testCreateTicket() {
        Client client = clientService.createClient("Ticket Client");
        Planet fromPlanet = planetService.createPlanet("FROMPL", "From Planet");
        Planet toPlanet = planetService.createPlanet("TOPL", "To Planet");

        Ticket ticket = new Ticket(LocalDateTime.now(), client, fromPlanet, toPlanet);
        ticketService.createTicket(ticket);
        assertNotNull(ticket.getId());
    }

    @Test
    void testGetTicket() {
        Client client = clientService.createClient("Ticket Client");
        Planet fromPlanet = planetService.createPlanet("FROMPL", "From Planet");
        Planet toPlanet = planetService.createPlanet("TOPL", "To Planet");

        Ticket ticket = new Ticket(LocalDateTime.now(), client, fromPlanet, toPlanet);
        ticketService.createTicket(ticket);

        Ticket retrievedTicket = ticketService.getTicket(ticket.getId());
        assertEquals(client.getId(), retrievedTicket.getClient().getId());
    }

    @Test
    void testUpdateTicket() {
        Client client = clientService.createClient("Ticket Client");
        Planet fromPlanet = planetService.createPlanet("FROMPL", "From Planet");
        Planet toPlanet = planetService.createPlanet("TOPL", "To Planet");

        Ticket ticket = new Ticket(LocalDateTime.now(), client, fromPlanet, toPlanet);
        ticketService.createTicket(ticket);

        LocalDateTime newCreatedAt = LocalDateTime.now().minusDays(1);
        Ticket updatedTicket = ticketService.updateTicket(ticket.getId(), newCreatedAt, client, fromPlanet, toPlanet);
        assertEquals(newCreatedAt, updatedTicket.getCreatedAt());
    }

    @Test
    void testDeleteTicket() {
        Client client = clientService.createClient("Ticket Client");
        Planet fromPlanet = planetService.createPlanet("FROMPL", "From Planet");
        Planet toPlanet = planetService.createPlanet("TOPL", "To Planet");

        Ticket ticket = new Ticket(LocalDateTime.now(), client, fromPlanet, toPlanet);
        ticketService.createTicket(ticket);

        ticketService.deleteTicket(ticket.getId());
        assertNull(ticketService.getTicket(ticket.getId()));
    }

    @Test
    void testTicketValidation() {
        Client client = clientService.createClient("Ticket Client");
        Planet fromPlanet = planetService.createPlanet("FROMPL", "From Planet");
        Planet toPlanet = planetService.createPlanet("TOPL", "To Planet");

        Ticket ticketWithNullClient = new Ticket(LocalDateTime.now(), null, fromPlanet, toPlanet);
        assertThrows(IllegalArgumentException.class, () -> ticketService.createTicket(ticketWithNullClient));

        Ticket ticketWithNullFromPlanet = new Ticket(LocalDateTime.now(), client, null, toPlanet);
        assertThrows(IllegalArgumentException.class, () -> ticketService.createTicket(ticketWithNullFromPlanet));

        Ticket ticketWithNullToPlanet = new Ticket(LocalDateTime.now(), client, fromPlanet, null);
        assertThrows(IllegalArgumentException.class, () -> ticketService.createTicket(ticketWithNullToPlanet));
    }
}
