package com.example.backend.domain;

import java.time.LocalDate;

public interface BusService {
    Bus create(LocalDate departureDate, int seats, double price);

}
