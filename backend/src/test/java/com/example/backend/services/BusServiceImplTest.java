package com.example.backend.services;

import com.example.backend.domain.bus.Bus;
import com.example.backend.repository.BusJPARepository;
import com.example.backend.repository.entities.BusEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BusServiceImplTest {

    @Mock
    private BusJPARepository busRepository;

    @InjectMocks
    private BusServiceImpl busService;

    @Test
    void shouldCreateBus() {
        final var id = UUID.randomUUID();
        final var departureDate = LocalDate.now();
        final var seats = 1;
        final var price = 1.05;

        final var expectedBus = new Bus(id, departureDate, seats, price);
        final var busEntity = new BusEntity(id, departureDate, seats, price);

        when(busRepository.save(Mockito.any(BusEntity.class))).thenReturn(busEntity);

        final var actual = busService.create(departureDate, seats, price);

        assertEquals(expectedBus, actual);
    }

    @Test
    void shouldGetAllBusses() {
        final var busId = UUID.randomUUID();
        final var departureDate = LocalDate.now();

        final var expectedBusList = List.of(new Bus(busId, departureDate, 1, 1.05));
        final var busEntityList = List.of(new BusEntity(busId, departureDate, 1, 1.05));

        when(busRepository.findAll()).thenReturn(busEntityList);

        final var actual = busService.getAll();

        assertEquals(expectedBusList, actual);
    }

    @Test
    void shouldGetBusById() {
        final var id = UUID.randomUUID();
        final var departureDate = LocalDate.now();
        final var seats = 1;
        final var price = 1.05;

        final var expectedBus = Optional.of(new Bus(id, departureDate, seats, price));
        final var mayBeBusEntity = Optional.of(new BusEntity(id, departureDate, seats, price));

        when(busRepository.findById(id)).thenReturn(mayBeBusEntity);

        final var actual = busService.getById(id);

        assertEquals(expectedBus, actual);
    }

}