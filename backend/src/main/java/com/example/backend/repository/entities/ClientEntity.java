package com.example.backend.repository.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name= "Clients")
public class ClientEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    @OneToMany
    private Set<ReservationEntity> reservations;

    protected ClientEntity() {
    }

    @PersistenceCreator
    public ClientEntity(UUID id, String name, String email, Set<ReservationEntity> reservations) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reservations = reservations;
    }

    public ClientEntity(String name, String email) {
        this.name = name;
        this.email = email;
        this.reservations = Collections.emptySet();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<ReservationEntity> getReservations() {
        return reservations;
    }
}
