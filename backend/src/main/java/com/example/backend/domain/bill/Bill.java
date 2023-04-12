package com.example.backend.domain.bill;

import com.example.backend.domain.reservation.PaymentMethod;

import java.util.Objects;
import java.util.UUID;

public class Bill {

    private final UUID reservationId;

    private final PaymentMethod paymentMethod;

    public Bill(UUID reservationId, PaymentMethod paymentMethod) {
        this.reservationId = reservationId;
        this.paymentMethod = paymentMethod;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(reservationId, bill.reservationId) && paymentMethod == bill.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, paymentMethod);
    }
}