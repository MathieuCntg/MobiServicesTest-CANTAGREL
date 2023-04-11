package com.example.backend.services;

import com.example.backend.domain.client.Client;
import com.example.backend.domain.client.ClientService;
import com.example.backend.repository.ClientJPARepository;
import com.example.backend.repository.entities.ClientEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientJPARepository clientJPARepository;

    public ClientServiceImpl(ClientJPARepository clientJPARepository) {
        this.clientJPARepository = clientJPARepository;
    }
    @Override
    public Client create(String email, String name) {
        return convertClientEntityToClient(clientJPARepository.save(new ClientEntity(email, name)));
    }

    private Client convertClientEntityToClient(ClientEntity clientEntity) {
        return new Client(clientEntity.getId(), clientEntity.getName(), clientEntity.getEmail());
    }
}
