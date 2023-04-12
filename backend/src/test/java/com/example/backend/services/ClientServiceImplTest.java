package com.example.backend.services;

import com.example.backend.domain.client.Client;
import com.example.backend.domain.reservation.Reservation;
import com.example.backend.repository.ClientJPARepository;
import com.example.backend.repository.entities.ClientEntity;
import com.example.backend.repository.entities.ReservationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
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
        final Set<Reservation> reservations = Collections.emptySet();
        final Set<ReservationEntity> reservationEntities = Collections.emptySet();
        final var clientEntity = new ClientEntity(id, name, email, reservationEntities);

        final var expectedClient = new Client(id, name, email, reservations);

        when(clientJPARepository.save(Mockito.any(ClientEntity.class))).thenReturn(clientEntity);

        final var actual = clientService.create(email, name);

        assertEquals(expectedClient, actual);
    }

    @Test
    void shouldGetClientById() {
        final var id = UUID.randomUUID();
        final var name = "John Doe";
        final var email = "john@email.com";
        final Set<Reservation> reservations = Collections.emptySet();
        final Set<ReservationEntity> reservationEntities = Collections.emptySet();
        final var clientEntity = Optional.of(new ClientEntity(id, name, email, reservationEntities));

        final var expectedClient = Optional.of(new Client(id, name, email, reservations));

        when(clientJPARepository.findById(id)).thenReturn(clientEntity);

        final var actual = clientService.getById(id);

        assertEquals(expectedClient, actual);
    }
}