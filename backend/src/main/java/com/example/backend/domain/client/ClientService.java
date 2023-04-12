package com.example.backend.domain.client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {

    Client create(String email, String name);

    Optional<Client> getById(UUID id);

}
