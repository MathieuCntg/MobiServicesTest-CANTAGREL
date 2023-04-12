package com.example.backend.domain.bus;

import java.time.LocalDate;
import java.util.List;

public interface BusService {
    Bus create(LocalDate departureDate, int seats, double price);

    List<Bus> getAll();

}
