package com.example.backend.dtos;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record CreateReservationDTO(LocalDate departureDate, UUID clientID, Set<UUID> bussesIds) {
}

