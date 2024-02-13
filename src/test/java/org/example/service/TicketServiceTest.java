package org.example.service;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TicketServiceTest {

    static TicketService ticketService = new TicketService();
    static ClientService clientService = new ClientService();
    static PlanetService planetService = new PlanetService();

    @ParameterizedTest
    @MethodSource("generateTickets")
    void test_add_ticket(Ticket ticket) {
        ticketService.add(ticket);

        Long id = ticket.getId();
        Ticket ticketById = ticketService.getById(id);

        assertEquals(ticket, ticketById);

        ticketService.deleteById(id);
    }

    @ParameterizedTest
    @MethodSource("generateTickets")
    void test_update_ticket(Ticket ticket) {
        final Long id = 7L;
        ticket.setId(id);
        ticketService.update(ticket);

        Ticket ticketById = ticketService.getById(id);

        ticket.setCreatedAt(ticketById.getCreatedAt());

        assertEquals(ticket, ticketById);
    }

    @ParameterizedTest
    @MethodSource("generateTickets")
    void test_delete_ticket(Ticket ticket) {
        ticketService.add(ticket);

        Long id = ticket.getId();
        ticketService.deleteById(id);

        assertNull(ticketService.getById(id));
    }

    private static Stream<Arguments> generateTickets() {
        return Stream.of(
                Arguments.of(generateTicket(1L, "VEN", "NEP")),
                Arguments.of(generateTicket(2L, "MER", "JUP")),
                Arguments.of(generateTicket(3L, "SAT", "VEN"))
        );
    }

    private static Ticket generateTicket(Long clientId, String fromPlanetId, String toPlanetId) {
        return buildTicket(
                clientService.getById(clientId),
                planetService.getById(fromPlanetId),
                planetService.getById(toPlanetId)
        );
    }

    private static Ticket buildTicket(Client client, Planet fromPlanet, Planet toPlanet) {
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);

        return ticket;
    }
}