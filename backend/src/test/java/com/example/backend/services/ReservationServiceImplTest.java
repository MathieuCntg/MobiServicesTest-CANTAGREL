package com.example.backend.services;

import com.example.backend.domain.bus.Bus;
import com.example.backend.domain.reservation.Reservation;
import com.example.backend.repository.BusJPARepository;
import com.example.backend.repository.ClientJPARepository;
import com.example.backend.repository.ReservationJPARepository;
import com.example.backend.repository.entities.BusEntity;
import com.example.backend.repository.entities.ClientEntity;
import com.example.backend.repository.entities.ReservationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationJPARepository reservationJPARepository;

    @Mock
    private BusJPARepository busJPARepository;

    @Mock
    private ClientJPARepository clientJPARepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    void shouldCreate() {
        final var reservationId = UUID.randomUUID();
        final var departureDate = LocalDate.now();

        final var clientId = UUID.randomUUID();
        final var clientEntity = new ClientEntity(clientId, "John", "email", Collections.emptySet());

        final var busId = UUID.randomUUID();
        final var busEntity = new BusEntity(busId, departureDate, 1, 1.05);
        final var bus = new Bus(busId, departureDate, busEntity.getSeats(), busEntity.getPrice());
        final var busIdSet = new HashSet<>(List.of(busId));

        final var reservationEntity = new ReservationEntity(reservationId, clientEntity, departureDate, new HashSet<>(List.of(busEntity)));
        final var expectedReservation = new Reservation(reservationId, clientEntity.getId(), departureDate, new HashSet<>(List.of(bus)));

        when(busJPARepository.findAllById(busIdSet)).thenReturn(List.of(busEntity));
        when(clientJPARepository.findById(clientId)).thenReturn(Optional.of(clientEntity));
        when(reservationJPARepository.save(Mockito.any(ReservationEntity.class))).thenReturn(reservationEntity);

        final var actual = reservationService.create(departureDate, clientId, busIdSet);

        assertEquals(expectedReservation, actual);
    }

    @Test
    void shouldGetAll() {
        final var reservationId = UUID.randomUUID();
        final var clientEntity = new ClientEntity(UUID.randomUUID(), "John", "email", Collections.emptySet());
        final var departureDate = LocalDate.now();
        final var busEntity = new BusEntity(UUID.randomUUID(), LocalDate.now(), 1, 1.05);
        final var bus = new Bus(busEntity.getId(), busEntity.getDepartureDate(), busEntity.getSeats(), busEntity.getPrice());

        final var reservationEntity = new ReservationEntity(reservationId, clientEntity, departureDate, new HashSet<>(List.of(busEntity)));
        final var reservation = new Reservation(reservationId, clientEntity.getId(), departureDate, new HashSet<>(List.of(bus)));

        final var reservationEntityList = List.of(reservationEntity);
        final var expectedReservationList = List.of(reservation);

        when(reservationJPARepository.findAll()).thenReturn(reservationEntityList);

        final var actual = reservationService.getAll();

        assertEquals(expectedReservationList, actual);
    }
}