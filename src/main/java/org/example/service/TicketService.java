package org.example.service;

import org.example.dao.impl.TicketDAO;
import org.example.entity.Ticket;

import java.util.List;

import static org.example.util.ServiceUtil.isTicketValid;

public class TicketService {

    private final TicketDAO ticketDAO = new TicketDAO();

    public Ticket getById(Long id) {
        return ticketDAO.findById(id);
    }

    public List<Ticket> getAll() {
        return ticketDAO.findAll();
    }

    public void add(Ticket ticket) {
        if (!isTicketValid(ticket)) {
            throw new IllegalArgumentException("Invalid ticket state");
        }
        ticketDAO.save(ticket);
    }

    public void update(Ticket ticket) {
        Ticket ticketById = ticketDAO.findById(ticket.getId());

        if (ticketById == null) {
            throw new IllegalStateException("Ticket with id '" + ticket.getId() + "' does not exist");
        }

        if (!isTicketValid(ticket)) {
            throw new IllegalArgumentException("Invalid ticket state");
        }
        ticketDAO.update(ticket);
    }

    public void delete(Ticket ticket) {
        ticketDAO.delete(ticket);
    }

    public Ticket deleteById(Long id) {
        Ticket ticketById = ticketDAO.findById(id);

        if (ticketById == null) {
            throw new IllegalStateException("Ticket with id '" + id + "' does not exist");
        }
        return ticketDAO.deleteById(id);
    }
}
