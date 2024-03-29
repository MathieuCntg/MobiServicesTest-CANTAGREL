package com.example.backend.controllers;

import com.example.backend.domain.client.Client;
import com.example.backend.domain.client.ClientService;
import com.example.backend.dtos.CreateClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String SERVER_URI = "/clients";

    @Test
    void shouldCreateClient() throws Exception {
        final var id = UUID.randomUUID();
        final var name = "John Doe";
        final var email = "john@email.com";

        final var expectedClient = new Client(id, name, email, Collections.emptySet());

        when(clientService.create(email, name)).thenReturn(expectedClient);

        mockMvc.perform(
                        post(SERVER_URI)
                                .content(objectMapper.writeValueAsString(new CreateClientDTO(email, name)))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedClient), true));
    }

    @Test
    void shouldGetClientById() throws Exception {
        final var id = UUID.randomUUID();

        final var expectedClient = Optional.of(new Client(id, "John Doe", "john@email.com", Collections.emptySet()));

        when(clientService.getById(id)).thenReturn(expectedClient);

        mockMvc.perform(
                        get(SERVER_URI + '/' + id)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedClient), true));
    }

    @Test
    void shouldGet404WhenGettingNonExistingClient() throws Exception {
        final var id = UUID.randomUUID();

        when(clientService.getById(id)).thenReturn(Optional.empty());

        mockMvc.perform(
                        get(SERVER_URI + '/' + id)
                )
                .andExpect(status().isNotFound());
    }

}