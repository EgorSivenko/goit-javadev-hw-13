package org.example.service;

import org.example.entity.Client;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientServiceTest {

    static ClientService clientService;

    @BeforeAll
    static void setup() {
        clientService = new ClientService();
    }

    @AfterAll
    static void cleanup() {
        clientService = null;
    }

    @ParameterizedTest
    @ValueSource(strings = {"John", "Henry", "Oliver"})
    void test_add_client(String name) {
        Client client = buildClient(name);
        clientService.add(client);

        Long id = client.getId();
        Client clientById = clientService.getById(id);

        assertEquals(client.getName(), clientById.getName());

        clientService.deleteById(id);
    }

    @ParameterizedTest
    @CsvSource(value = {"1-Robert", "2-Liam", "3-Mateo"}, delimiter = '-')
    void test_update_client(Long id, String name) {
        Client client = clientService.getById(id);
        client.setName(name);
        clientService.update(client);

        Client clientById = clientService.getById(id);

        assertEquals(name, clientById.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sebastian", "Benjamin", "James"})
    void test_delete_client(String name) {
        Client client = buildClient(name);
        clientService.add(client);

        Long clientId = client.getId();
        clientService.deleteById(clientId);

        assertNull(clientService.getById(clientId));
    }

    private Client buildClient(String name) {
        Client client = new Client();
        client.setName(name);
        return client;
    }
}