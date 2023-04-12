package com.example.backend.services;

import com.example.backend.domain.bus.Bus;
import com.example.backend.domain.client.Client;
import com.example.backend.domain.client.ClientService;
import com.example.backend.domain.reservation.Reservation;
import com.example.backend.repository.ClientJPARepository;
import com.example.backend.repository.entities.BusEntity;
import com.example.backend.repository.entities.ClientEntity;
import com.example.backend.repository.entities.ReservationEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientJPARepository clientJPARepository;

    public ClientServiceImpl(ClientJPARepository clientJPARepository) {
        this.clientJPARepository = clientJPARepository;
    }
    @Override
    public Client create(String email, String name) {
        return convertClientEntityToClient(clientJPARepository.save(new ClientEntity(email, name)));
    }

    @Override
    public Optional<Client> getById(UUID id) {
        return clientJPARepository.findById(id).map(this::convertClientEntityToClient);
    }

    private Client convertClientEntityToClient(ClientEntity clientEntity) {
        return new Client(clientEntity.getId(), clientEntity.getName(), clientEntity.getEmail(), clientEntity.getReservations().stream().map(this::convertReservationEntityToReservation).collect(Collectors.toSet()));
    }

    private Reservation convertReservationEntityToReservation(ReservationEntity reservationEntity) {
        return new Reservation(reservationEntity.getId(), reservationEntity.getClient().getId(), reservationEntity.getDepartureDate(), reservationEntity.getBusses().stream().map(this::convertBusEntityToBus).collect(Collectors.toSet()), reservationEntity.getPaymentMethod());
    }

    private Bus convertBusEntityToBus(BusEntity busEntity) {
        return new Bus(busEntity.getId(), busEntity.getDepartureDate(), busEntity.getSeats(), busEntity.getPrice());
    }
}
