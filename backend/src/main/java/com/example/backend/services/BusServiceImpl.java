package com.example.backend.services;

import com.example.backend.domain.Bus;
import com.example.backend.domain.BusService;
import com.example.backend.repository.BusJPARepository;
import com.example.backend.repository.entities.BusEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BusServiceImpl implements BusService {

    private final BusJPARepository jpaRepository;

    public BusServiceImpl(BusJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Bus create(LocalDate departureDate, int seats, double price) {
        final var busEntity = new BusEntity(departureDate, seats, calculatePrice(price));
        return convertBusEntityToBus(jpaRepository.save(busEntity));
    }

    private Bus convertBusEntityToBus(BusEntity busEntity) {
        return new Bus(busEntity.getId(), busEntity.getDepartureDate(), busEntity.getSeats(), busEntity.getPrice());
    }

    private double calculatePrice(double price) {
        return price > 100 ? price * 0.95 : price;
    }
}
