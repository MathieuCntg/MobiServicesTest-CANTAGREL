package com.example.backend.domain.bus;

import java.time.LocalDate;

public interface BusService {
    Bus create(LocalDate departureDate, int seats, double price);

}
