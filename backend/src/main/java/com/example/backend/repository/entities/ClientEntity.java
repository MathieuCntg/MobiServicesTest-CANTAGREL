package com.example.backend.repository.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.UUID;

@Entity
@Table(name= "Clients")
public class ClientEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    protected ClientEntity() {
    }

    @PersistenceCreator
    public ClientEntity(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public ClientEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
