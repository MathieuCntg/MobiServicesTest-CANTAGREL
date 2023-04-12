package com.example.backend.services;

import com.example.backend.domain.bill.Bill;
import com.example.backend.domain.bill.BillService;
import com.example.backend.repository.ReservationJPARepository;
import com.example.backend.repository.entities.ReservationEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillService {
    private final ReservationJPARepository reservationJPARepository;

    public BillServiceImpl(ReservationJPARepository reservationJPARepository) {
        this.reservationJPARepository = reservationJPARepository;
    }

    @Override
    public List<Bill> getPaidBillByClientId(UUID clientId) {
        return reservationJPARepository.findAllByClient_IdAndPaymentMethodIsNotNull(clientId).stream().map(this::convertReservationEntityToBill).toList();
    }

    private Bill convertReservationEntityToBill(ReservationEntity reservationEntity) {
        return new Bill(reservationEntity.getId(), reservationEntity.getPaymentMethod());
    }
}
