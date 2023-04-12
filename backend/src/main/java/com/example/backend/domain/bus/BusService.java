package com.example.backend.domain.bus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BusService {
    Bus create(LocalDate departureDate, int seats, double price);

    List<Bus> getAll();

    Optional<Bus> getById(UUID id);

    void deleteBusById(UUID id);
}
