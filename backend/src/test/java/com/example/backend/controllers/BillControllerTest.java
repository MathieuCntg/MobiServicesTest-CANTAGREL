package com.example.backend.controllers;

import com.example.backend.domain.bill.Bill;
import com.example.backend.domain.bill.BillService;
import com.example.backend.domain.reservation.PaymentMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BillController.class)
class BillControllerTest {

    @MockBean
    private BillService billService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String SERVER_URI = "/bills";

    @Test
    void shouldGetAllPaidBillsGivenAClientId() throws Exception {
        final var clientId = UUID.randomUUID();

        final var expectedBillList = List.of(new Bill(UUID.randomUUID(), PaymentMethod.PAYPAL));

        when(billService.getPaidBillByClientId(clientId)).thenReturn(expectedBillList);

        mockMvc.perform(
                        get(SERVER_URI + "/" + clientId)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedBillList), true));

    }
}