package com.example.backend.controllers;

import com.example.backend.domain.client.Client;
import com.example.backend.domain.client.ClientService;
import com.example.backend.dtos.CreateClientDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody CreateClientDTO createClientDTO) {
        return clientService.create(createClientDTO.email(), createClientDTO.name());
    }
}
