package com.example.backend.controllers;

import com.example.backend.domain.bus.Bus;
import com.example.backend.domain.bus.BusService;
import com.example.backend.dtos.CreateBusDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BusController.class)
class BusControllerTest {

    @MockBean
    private BusService busService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String SERVER_URI = "/busses";

    @Test
    void shouldCreateBus() throws Exception {
        final var id = UUID.randomUUID();
        final var departureDate = LocalDate.now();
        final var seats = 1;
        final var price = 1.05;

        final var expectedBus = new Bus(id, departureDate, seats, price);

        when(busService.create(departureDate, seats, price)).thenReturn(expectedBus);

        mockMvc.perform(
                        post(SERVER_URI)
                                .content(objectMapper.writeValueAsString(new CreateBusDTO(departureDate, seats, price)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedBus), true));
    }

    @Test
    void shouldGetAllBusses() throws Exception {
        final var expectedBusList = List.of(new Bus(UUID.randomUUID(), LocalDate.now(), 1, 1.05));

        when(busService.getAll()).thenReturn(expectedBusList);

        mockMvc.perform(
                        get(SERVER_URI)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedBusList), true));
    }
}