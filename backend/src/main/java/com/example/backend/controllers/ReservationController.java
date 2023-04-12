package com.example.backend.controllers;

import com.example.backend.domain.reservation.Reservation;
import com.example.backend.domain.reservation.ReservationService;
import com.example.backend.dtos.CreateReservationDTO;
import com.example.backend.dtos.PayReservationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "*")
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

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByID(@PathVariable UUID id) {
        reservationService.deleteById(id);
    }

    @PostMapping("/{id}/pay")
    public Reservation pay(@PathVariable UUID id, @RequestBody PayReservationDTO payReservationDTO) {
        return reservationService.pay(id, payReservationDTO.paymentMethod());
    }
}
