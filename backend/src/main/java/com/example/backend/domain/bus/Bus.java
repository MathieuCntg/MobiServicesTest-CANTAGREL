package com.example.backend.domain.bus;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Bus {

    private final UUID id;
    private final LocalDate departureDate;
    private final int seats;

    private final double price;

    public Bus(UUID id, LocalDate departureDate, int seats, double price) {
        this.id = id;
        this.departureDate = departureDate;
        this.seats = seats;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public int getSeats() {
        return seats;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return seats == bus.seats && Double.compare(bus.price, price) == 0 && Objects.equals(id, bus.id) && Objects.equals(departureDate, bus.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureDate, seats, price);
    }
}