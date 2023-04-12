package com.example.backend.controllers;

import com.example.backend.domain.bus.Bus;
import com.example.backend.domain.reservation.PaymentMethod;
import com.example.backend.domain.reservation.Reservation;
import com.example.backend.domain.reservation.ReservationService;
import com.example.backend.dtos.CreateReservationDTO;
import com.example.backend.dtos.PayReservationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @MockBean
    ReservationService reservationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String SERVER_URI = "/reservations";

    @Test
    void shouldCreate() throws Exception {
        final var reservationId = UUID.randomUUID();
        final var departureDate = LocalDate.now();
        final var clientId = UUID.randomUUID();
        final Set<UUID> bussesIds = Collections.emptySet();
        final Set<Bus> busses = Collections.emptySet();

        final var expectedReservation = new Reservation(reservationId, clientId, departureDate, busses, null);

        when(reservationService.create(departureDate, clientId, bussesIds)).thenReturn(expectedReservation);

        mockMvc.perform(
                        post(SERVER_URI)
                                .content(objectMapper.writeValueAsString(new CreateReservationDTO(departureDate, clientId, bussesIds)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedReservation), true));
    }

    @Test
    void shouldGetAll() throws Exception {
        final var expectedReservationList = List.of(new Reservation(UUID.randomUUID(), UUID.randomUUID(), LocalDate.now(), Collections.emptySet(), PaymentMethod.PAYPAL));

        when(reservationService.getAll()).thenReturn(expectedReservationList);

        mockMvc.perform(
                        get(SERVER_URI)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedReservationList), true));
    }

    @Test
    void shouldDeleteByID() throws Exception {
        final var id = UUID.randomUUID();

        mockMvc.perform(
                        delete(SERVER_URI+"/"+id)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldPay() throws Exception {
        final var id = UUID.randomUUID();
        final var paymentMethod = PaymentMethod.PAYPAL;
        final var paymentInformation = Map.of("email", "email");

        final var departureDate = LocalDate.now();
        final var clientId = UUID.randomUUID();
        final Set<Bus> busses = Collections.emptySet();

        final var expectedReservation = new Reservation(id, clientId, departureDate, busses, paymentMethod);

        when(reservationService.pay(id, paymentMethod)).thenReturn(expectedReservation);

        mockMvc.perform(
                        post(SERVER_URI + '/' + id + "/pay")
                                .content(objectMapper.writeValueAsString(new PayReservationDTO(paymentMethod, paymentInformation)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedReservation), true));
    }

}