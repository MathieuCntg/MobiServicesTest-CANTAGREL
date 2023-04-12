package com.example.backend.repository;

import com.example.backend.repository.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationJPARepository extends JpaRepository<ReservationEntity, UUID> {

    List<ReservationEntity> findAllByClient_IdAndPaymentMethodIsNotNull(UUID client_id);

}
