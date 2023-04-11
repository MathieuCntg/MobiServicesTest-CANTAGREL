package com.example.backend.repository;

import com.example.backend.repository.entities.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusJPARepository extends JpaRepository<BusEntity, UUID> {
}
