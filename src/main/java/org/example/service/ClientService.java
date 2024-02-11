package org.example.service;

import org.example.dao.impl.ClientDAO;
import org.example.entity.Client;

import java.util.List;
import java.util.Objects;

import static org.example.util.ServiceUtil.isNameValid;

public class ClientService {

    private final ClientDAO clientDAO = new ClientDAO();

    public Client getById(Long id) {
        return clientDAO.findById(id);
    }

    public List<Client> getAll() {
        return clientDAO.findAll();
    }

    public void add(Client client) {
        if (!isNameValid(client.getName())) {
            throw new IllegalArgumentException("Invalid client name");
        }
        Client clientByName = clientDAO.findByName(client.getName());

        if (clientByName != null) {
            throw new IllegalStateException("Client with name '" + client.getName() + "' already exists");
        }
        clientDAO.save(client);
    }

    public void update(Client client) {
        Client clientById = clientDAO.findById(client.getId());

        if (clientById == null) {
            throw new IllegalStateException("Client with id '" + client.getId() + "' does not exist");
        }

        if (isNameValid(client.getName()) && !Objects.equals(client.getName(), clientById.getName())) {
            Client clientByName = clientDAO.findByName(client.getName());

            if (clientByName != null) {
                throw new IllegalStateException("Client with name '" + client.getName() + "' already exists");
            }
            clientDAO.update(client);
        }
    }

    public void delete(Client client) {
        clientDAO.delete(client);
    }

    public Client deleteById(Long id) {
        Client clientById = clientDAO.findById(id);

        if (clientById == null) {
            throw new IllegalStateException("Client with id '" + id + "' does not exist");
        }
        return clientDAO.deleteById(id);
    }
}
