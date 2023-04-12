package com.example.backend.dtos;

import com.example.backend.domain.reservation.PaymentMethod;

import java.util.Map;

public record PayReservationDTO(PaymentMethod paymentMethod, Map<String, String> paymentInformation) {
}
