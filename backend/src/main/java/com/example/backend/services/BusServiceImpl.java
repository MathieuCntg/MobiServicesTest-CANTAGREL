package com.example.backend.services;

import com.example.backend.domain.bus.Bus;
import com.example.backend.domain.bus.BusService;
import com.example.backend.repository.BusJPARepository;
import com.example.backend.repository.entities.BusEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public List<Bus> getAll() {
        return jpaRepository.findAll().stream().map(this::convertBusEntityToBus).toList();
    }

    @Override
    public Optional<Bus> getById(UUID id) {
        return jpaRepository.findById(id).map(this::convertBusEntityToBus);
    }

    @Override
    public void deleteBusById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private Bus convertBusEntityToBus(BusEntity busEntity) {
        return new Bus(busEntity.getId(), busEntity.getDepartureDate(), busEntity.getSeats(), busEntity.getPrice());
    }

    private double calculatePrice(double price) {
        return price > 100 ? price * 0.95 : price;
    }
}
