package com.example.backend.repository.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Busses")
public class BusEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate departureDate;
    private int seats;
    private double price;

    protected BusEntity() {}

    @PersistenceCreator
    public BusEntity(UUID id, LocalDate departureDate, int seats, double price) {
        this.id = id;
        this.departureDate = departureDate;
        this.seats = seats;
        this.price = price;
    }

    public BusEntity(LocalDate departureDate, int seats, double price) {
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
}
