package com.example.backend.services;

import com.example.backend.domain.client.Client;
import com.example.backend.repository.ClientJPARepository;
import com.example.backend.repository.entities.ClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientJPARepository clientJPARepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void shouldCreate() {
        final var id = UUID.randomUUID();
        final var name = "John Doe";
        final var email = "john@email.com";
        final var clientEntity = new ClientEntity(id, name, email);

        final var expectedClient = new Client(id, name, email);

        when(clientJPARepository.save(Mockito.any(ClientEntity.class))).thenReturn(clientEntity);

        final var actual = clientService.create(email, name);

        assertEquals(expectedClient, actual);
    }
}