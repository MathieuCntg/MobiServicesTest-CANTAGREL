package com.example.backend.services;

import com.example.backend.domain.bill.Bill;
import com.example.backend.domain.reservation.PaymentMethod;
import com.example.backend.repository.ReservationJPARepository;
import com.example.backend.repository.entities.BusEntity;
import com.example.backend.repository.entities.ClientEntity;
import com.example.backend.repository.entities.ReservationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BillServiceImplTest {

    @Mock
    private ReservationJPARepository reservationJPARepository;

    @InjectMocks
    private BillServiceImpl billService;

    @Test
    void shouldGetAllPaidBillsGivenAClientId() {
        final var reservationId = UUID.randomUUID();
        final var clientId = UUID.randomUUID();
        final var clientEntity = new ClientEntity(clientId, "John", "email", Collections.emptySet());
        final var departureDate = LocalDate.now();
        final var busEntity = new BusEntity(UUID.randomUUID(), LocalDate.now(), 1, 1.05);

        final var reservationEntity = new ReservationEntity(reservationId, clientEntity, departureDate, new HashSet<>(List.of(busEntity)), PaymentMethod.PAYPAL);

        final var expectedBillList = List.of(new Bill(reservationId, PaymentMethod.PAYPAL));

        when(reservationJPARepository.findAllByClient_IdAndPaymentMethodIsNotNull(clientId)).thenReturn(List.of(reservationEntity));

        final var actual = billService.getPaidBillByClientId(clientId);

        assertEquals(expectedBillList, actual);
    }
}