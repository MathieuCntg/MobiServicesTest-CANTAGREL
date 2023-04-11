package com.example.backend.repository.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private ClientEntity client;

    private LocalDate departureDate;

    @ManyToMany
    private Set<BusEntity> busses;

    protected ReservationEntity() {
    }

    @PersistenceCreator
    public ReservationEntity(UUID id, ClientEntity client, LocalDate departureDate, Set<BusEntity> busses) {
        this.id = id;
        this.client = client;
        this.departureDate = departureDate;
        this.busses = busses;
    }

    public ReservationEntity(ClientEntity client, LocalDate departureDate, Set<BusEntity> busses) {
        this.client = client;
        this.departureDate = departureDate;
        this.busses = busses;
    }

    public UUID getId() {
        return id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public Set<BusEntity> getBusses() {
        return busses;
    }
}
