package com.example.backend.dtos;

import java.time.LocalDate;

public record CreateBusDTO(LocalDate departureDate, int seats, double price) {
}
