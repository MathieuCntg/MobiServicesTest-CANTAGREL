package com.example.backend.controllers;

import com.example.backend.domain.reservation.Reservation;
import com.example.backend.domain.reservation.ReservationService;
import com.example.backend.dtos.CreateReservationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@RequestBody CreateReservationDTO createReservationDTO) throws Exception {
        return reservationService.create(createReservationDTO.departureDate(), createReservationDTO.clientID(), createReservationDTO.bussesIds());
    }
}
