package org.example.service;

import org.example.model.Client;
import org.example.model.Planet;
import org.example.model.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

public class TicketCrudService {

    @PersistenceContext
    private EntityManager entityManager;

    public Ticket createTicket(Ticket ticket) {
        if (ticket.getClient() == null || ticket.getFromPlanet() == null || ticket.getToPlanet() == null) {
            throw new IllegalArgumentException("Client and planets must not be null.");
        }
        entityManager.persist(ticket);
        return ticket;
    }

    public Ticket getTicket(Long id) {
        return entityManager.find(Ticket.class, id);
    }

    public List<Ticket> getAllTickets() {
        Query query = entityManager.createQuery("SELECT t FROM Ticket t");
        return query.getResultList();
    }

    public Ticket updateTicket(Long id, LocalDateTime createdAt, Client client, Planet fromPlanet, Planet toPlanet) {
        Ticket ticket = getTicket(id);
        if (ticket != null) {
            ticket.setCreatedAt(createdAt);
            ticket.setClient(client);
            ticket.setFromPlanet(fromPlanet);
            ticket.setToPlanet(toPlanet);
            entityManager.merge(ticket);
        }
        return ticket;
    }

    public void deleteTicket(Long id) {
        Ticket ticket = getTicket(id);
        if (ticket != null) {
            entityManager.remove(ticket);
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
