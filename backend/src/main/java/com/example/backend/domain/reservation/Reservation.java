package com.example.backend.domain.reservation;

import com.example.backend.domain.bus.Bus;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Reservation {
    private final UUID id;

    private final UUID clientId;

    private final LocalDate departureDate;

    private final Set<Bus> busses;

    private final PaymentMethod paymentMethod;

    public Reservation(UUID id, UUID clientId, LocalDate departureDate, Set<Bus> busses, PaymentMethod paymentMethod) {
        this.id = id;
        this.clientId = clientId;
        this.departureDate = departureDate;
        this.busses = busses;
        this.paymentMethod = paymentMethod;
    }

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public Set<Bus> getBusses() {
        return busses;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(clientId, that.clientId) && Objects.equals(departureDate, that.departureDate) && Objects.equals(busses, that.busses) && paymentMethod == that.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, departureDate, busses, paymentMethod);
    }
}
