package com.example.backend.services;

import com.example.backend.domain.bus.Bus;
import com.example.backend.domain.reservation.PaymentMethod;
import com.example.backend.domain.reservation.Reservation;
import com.example.backend.domain.reservation.ReservationService;
import com.example.backend.repository.BusJPARepository;
import com.example.backend.repository.ClientJPARepository;
import com.example.backend.repository.ReservationJPARepository;
import com.example.backend.repository.entities.BusEntity;
import com.example.backend.repository.entities.ReservationEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationJPARepository reservationJPARepository;
    private final BusJPARepository busJPARepository;
    private final ClientJPARepository clientJPARepository;

    public ReservationServiceImpl(ReservationJPARepository reservationJPARepository, BusJPARepository busJPARepository, ClientJPARepository clientJPARepository) {
        this.reservationJPARepository = reservationJPARepository;
        this.busJPARepository = busJPARepository;
        this.clientJPARepository = clientJPARepository;
    }

    @Override
    public Reservation create(LocalDate departureDate, UUID clientID, Set<UUID> bussesIds) {
        final var busses = busJPARepository.findAllById(bussesIds);
        final var client = clientJPARepository.findById(clientID);

        return convertReservationEntityToReservation(reservationJPARepository.save(new ReservationEntity(client.orElseThrow(), departureDate, new HashSet<>(busses), null)));
    }

    @Override
    public List<Reservation> getAll() {
        return reservationJPARepository.findAll().stream().map(this::convertReservationEntityToReservation).toList();
    }

    @Override
    public void deleteById(UUID id) {
        reservationJPARepository.deleteById(id);
    }

    @Override
    public Reservation pay(UUID id, PaymentMethod paymentMethod) {

        final var maybeReservation = reservationJPARepository.findById(id);

        final var reservation = maybeReservation.orElseThrow();

        return convertReservationEntityToReservation(reservationJPARepository.save(new ReservationEntity(reservation.getId(), reservation.getClient(), reservation.getDepartureDate(), reservation.getBusses(), paymentMethod)));
    }

    @Override
    public Optional<Reservation> getById(UUID id) {
        return reservationJPARepository.findById(id).map(this::convertReservationEntityToReservation);
    }

    private Reservation convertReservationEntityToReservation(ReservationEntity reservationEntity) {
        return new Reservation(reservationEntity.getId(), reservationEntity.getClient().getId(), reservationEntity.getDepartureDate(), reservationEntity.getBusses().stream().map(this::convertBusEntityToBus).collect(Collectors.toSet()), reservationEntity.getPaymentMethod());
    }

    private Bus convertBusEntityToBus(BusEntity busEntity) {
        return new Bus(busEntity.getId(), busEntity.getDepartureDate(), busEntity.getSeats(), busEntity.getPrice());
    }
}
