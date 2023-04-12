package com.example.backend.repository.entities;

import com.example.backend.domain.reservation.PaymentMethod;
import jakarta.persistence.*;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDate;
import java.util.Objects;
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

    private PaymentMethod paymentMethod;

    protected ReservationEntity() {
    }

    @PersistenceCreator
    public ReservationEntity(UUID id, ClientEntity client, LocalDate departureDate, Set<BusEntity> busses, PaymentMethod paymentMethod) {
        this.id = id;
        this.client = client;
        this.departureDate = departureDate;
        this.busses = busses;
        this.paymentMethod = paymentMethod;
    }

    public ReservationEntity(ClientEntity client, LocalDate departureDate, Set<BusEntity> busses, PaymentMethod paymentMethod) {
        this.client = client;
        this.departureDate = departureDate;
        this.busses = busses;
        this.paymentMethod = paymentMethod;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntity that = (ReservationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(departureDate, that.departureDate) && Objects.equals(busses, that.busses) && paymentMethod == that.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, departureDate, busses, paymentMethod);
    }
}
