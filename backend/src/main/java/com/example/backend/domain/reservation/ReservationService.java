package com.example.backend.domain.reservation;

import com.example.backend.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ReservationService {

    Reservation create(LocalDate departureDate, UUID clientID, Set<UUID> bussesIds) throws NotFoundException;

    List<Reservation> getAll();

    void deleteById(UUID id);

    Reservation pay(UUID id, PaymentMethod paymentMethod) throws NotFoundException;

    Optional<Reservation> getById(UUID id);
}
