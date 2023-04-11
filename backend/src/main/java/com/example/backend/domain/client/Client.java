package com.example.backend.domain.client;

import com.example.backend.domain.reservation.Reservation;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Client {

    private final UUID id;

    private final String name;

    private final String email;

    private final Set<Reservation> reservations;

    public Client(UUID id, String name, String email, Set<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.reservations = reservations;
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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
