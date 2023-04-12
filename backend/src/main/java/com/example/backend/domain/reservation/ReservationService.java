package com.example.backend.domain.reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ReservationService {

    Reservation create(LocalDate departureDate, UUID clientID, Set<UUID> bussesIds) throws Exception;

    List<Reservation> getAll();

    void deleteById(UUID id);

    Reservation pay(UUID id, PaymentMethod paymentMethod);
}
