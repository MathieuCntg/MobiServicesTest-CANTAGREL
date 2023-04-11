package com.example.backend.controllers;

import com.example.backend.domain.bus.Bus;
import com.example.backend.domain.reservation.Reservation;
import com.example.backend.domain.reservation.ReservationService;
import com.example.backend.dtos.CreateReservationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        final var expectedReservation = new Reservation(reservationId, clientId, departureDate, busses);

        when(reservationService.create(departureDate, clientId, bussesIds)).thenReturn(expectedReservation);

        mockMvc.perform(
                        post(SERVER_URI)
                                .content(objectMapper.writeValueAsString(new CreateReservationDTO(departureDate, clientId, bussesIds)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedReservation), true));
    }

}