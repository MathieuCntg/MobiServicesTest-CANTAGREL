package com.example.backend.controllers;

import com.example.backend.domain.bus.Bus;
import com.example.backend.domain.bus.BusService;
import com.example.backend.dtos.CreateBusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/busses")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bus createBus(@RequestBody CreateBusDTO createBusDTO) {
        return busService.create(createBusDTO.departureDate(), createBusDTO.seats(), createBusDTO.price());
    }

    @GetMapping
    public List<Bus> getAllBusses() {
        return busService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable UUID id) {
        return ResponseEntity.of(busService.getById(id));
    }

}
